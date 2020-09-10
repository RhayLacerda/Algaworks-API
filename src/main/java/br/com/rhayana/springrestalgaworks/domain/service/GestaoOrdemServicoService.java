package br.com.rhayana.springrestalgaworks.domain.service;

import br.com.rhayana.springrestalgaworks.domain.exception.NegocioException;
import br.com.rhayana.springrestalgaworks.domain.model.Cliente;
import br.com.rhayana.springrestalgaworks.domain.model.Comentario;
import br.com.rhayana.springrestalgaworks.domain.model.OrdemServico;
import br.com.rhayana.springrestalgaworks.domain.model.StatusOrdemServico;
import br.com.rhayana.springrestalgaworks.domain.repository.ClienteRepository;
import br.com.rhayana.springrestalgaworks.domain.repository.ComentarioRepository;
import br.com.rhayana.springrestalgaworks.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new NegocioException("Ordem de serviço não encontrada"));
        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }
}
