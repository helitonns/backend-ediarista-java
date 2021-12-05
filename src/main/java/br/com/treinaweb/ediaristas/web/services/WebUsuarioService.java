package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.exceptions.SenhaIncorretaException;
import br.com.treinaweb.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.treinaweb.ediaristas.core.exceptions.UsuairoNaoEncontradoException;
import br.com.treinaweb.ediaristas.core.exceptions.UsuarioCadastradoException;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.web.dtos.AlterarSenhaForm;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private WebUsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------------------------------------------------------------

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroForm form) {
        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senhas não são iguais";
            var fieldError = new FieldError(
                    form.getClass().getName(),
                    "confirmacaoSenha",
                    form.getConfirmacaoSenha(),
                    false, null, null, mensagem);
            throw new SenhasNaoConferemException(mensagem, fieldError);
        }

        var model = mapper.toModel(form);

        model.setSenha(passwordEncoder.encode(model.getSenha()));
        model.setTipoUsuario(TipoUsuario.ADMIN);

        validarCamposUnicos(model);

        return repository.save(model);
    }

    public Usuario buscarPorId(Long id) {
        var mensagem = String.format("Usuário com ID  %d não encontrado", id);
        return repository.findById(id).orElseThrow(() -> new UsuairoNaoEncontradoException(mensagem));
    }

    public Usuario buscarPorEmail(String email) {
        var mensagem = String.format("Usuário com e-mail  %s não encontrado", email);
        return repository.findByEmail(email).orElseThrow(() -> new UsuairoNaoEncontradoException(mensagem));
    }

    public UsuarioEdicaoForm buscarFormPorId(Long id) {
        var usuario = buscarPorId(id);
        return mapper.toForm(usuario);
    }

    public Usuario editar(UsuarioEdicaoForm form, Long id) {
        var usuario = buscarPorId(id);
        var model = mapper.toModel(form);

        model.setId(usuario.getId());
        model.setTipoUsuario(usuario.getTipoUsuario());
        model.setSenha(usuario.getSenha());

        validarCamposUnicos(model);

        return repository.save(model);
    }

    public void excluirPorId(Long id) {
        var usuario = buscarPorId(id);
        repository.delete(usuario);
    }

    public void alterarSenha(AlterarSenhaForm form, String email) {
        var usuario = buscarPorEmail(email);

        var senhaAtual = usuario.getSenha();
        var senhaAntiga = form.getSenhaAntiga();
        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senhas não são iguais";
            var fieldError = new FieldError(
                    form.getClass().getName(),
                    "confirmacaoSenha",
                    form.getConfirmacaoSenha(),
                    false, null, null, mensagem);
            throw new SenhasNaoConferemException(mensagem, fieldError);
        }

        if (!passwordEncoder.matches(senhaAntiga, senhaAtual)) {
            var mensagem = "A senha antiga está incorreta";
            var fieldError = new FieldError(
                    form.getClass().getName(),
                    "senhaAntiga",
                    senhaAntiga,
                    false, null, null, mensagem);
            throw new SenhaIncorretaException(mensagem, fieldError);
        }

        usuario.setSenha(passwordEncoder.encode(senha));
        repository.save(usuario);
    }

    private void validarCamposUnicos(Usuario usuario) {
        if (repository.isEmailJaCadastrado(usuario.getEmail(), usuario.getId())) {
            var mensagem = "Já existe um usuário cadastrado com esse e-mail";
            var fieldError = new FieldError(
                    usuario.getClass().getName(),
                    "email",
                    usuario.getEmail(),
                    false, null, null, mensagem);
            throw new UsuarioCadastradoException(mensagem, fieldError);
        }
    }

}
