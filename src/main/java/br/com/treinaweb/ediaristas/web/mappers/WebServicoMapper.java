package br.com.treinaweb.ediaristas.web.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;

@Component
public class WebServicoMapper {

    public Servico toModel(ServicoForm form) {
        if (form == null) {
            throw new IllegalArgumentException();
        }
        var model = new Servico();

        model.setNome(form.getNome());
        model.setValorMinimo(form.getValorMinimo());
        model.setQtdHoras(form.getQtdHoras());
        model.setPorcentagemComissao(form.getPorcentagemComissao());
        model.setHorasQuarto(form.getHorasQuarto());
        model.setValorQuarto(form.getValorQuarto());
        model.setHorasSala(form.getHorasSala());
        model.setValorSala(form.getValorSala());
        model.setHorasBanheiro(form.getHorasBanheiro());
        model.setValorBanheiro(form.getValorBanheiro());
        model.setHorasCozinha(form.getHorasCozinha());
        model.setValorCozinha(form.getValorCozinha());
        model.setHorasQuintal(form.getHorasQuintal());
        model.setValorQuintal(form.getValorQuintal());
        model.setHorasOutros(form.getHorasOutros());
        model.setValorOutros(form.getValorOutros());
        model.setIcone(form.getIcone());
        model.setPosicao(form.getPosicao());
        return model;
    }

    public ServicoForm toForm(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException();
        }

        var form = new ServicoForm();

        form.setNome(servico.getNome());
        form.setValorMinimo(servico.getValorMinimo());
        form.setQtdHoras(servico.getQtdHoras());
        form.setPorcentagemComissao(servico.getPorcentagemComissao());
        form.setHorasQuarto(servico.getHorasQuarto());
        form.setValorQuarto(servico.getValorQuarto());
        form.setHorasSala(servico.getHorasSala());
        form.setValorSala(servico.getValorSala());
        form.setHorasBanheiro(servico.getHorasBanheiro());
        form.setValorBanheiro(servico.getValorBanheiro());
        form.setHorasCozinha(servico.getHorasCozinha());
        form.setValorCozinha(servico.getValorCozinha());
        form.setHorasQuintal(servico.getHorasQuintal());
        form.setValorQuintal(servico.getValorQuintal());
        form.setHorasOutros(servico.getHorasOutros());
        form.setValorOutros(servico.getValorOutros());
        form.setIcone(servico.getIcone());
        form.setPosicao(servico.getPosicao());
        return form;
    }
}
