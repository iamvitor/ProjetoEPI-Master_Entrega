// Preencher os campos do modal de visualização com os dados do empréstimo
const visualizarEmprestimoModal = document.getElementById('visualizarEmprestimoModal');
if (visualizarEmprestimoModal) {
    visualizarEmprestimoModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const id = button.getAttribute('data-id');
        const colaborador = button.getAttribute('data-colaborador');
        const equipamento = button.getAttribute('data-equipamento');
        const dataEmprestimo = button.getAttribute('data-dataemprestimo');
        const dataDevolucao = button.getAttribute('data-datadevolucao');

        // Atualiza os campos no modal
        document.getElementById('visualizarEmprestimoId').textContent = id || 'Não disponível';
        document.getElementById('visualizarEmprestimoColaborador').textContent = colaborador || 'Não disponível';
        document.getElementById('visualizarEmprestimoEquipamento').textContent = equipamento || 'Não disponível';
        document.getElementById('visualizarEmprestimoDataEmprestimo').textContent = dataEmprestimo || 'Não disponível';
        document.getElementById('visualizarEmprestimoDataDevolucao').textContent = dataDevolucao || 'Não disponível';
    });
}



window.addEventListener('message', function (event) {
    if (event.data === 'fechar-modal') {
        const cadastrarModal = bootstrap.Modal.getInstance(document.getElementById('cadastrarEquipamentoModal'));
        if (cadastrarModal) {
            cadastrarModal.hide(); // Fecha o modal
        }
        location.reload(); // Atualiza a página
    }
});

document.querySelectorAll('.btn-outline-warning').forEach(function(button) {
    button.addEventListener('click', function() {
        const id = this.getAttribute('data-id');
        const iframeSrc = `/emprestimos/devolucao/${id}`; 
        document.getElementById('devolverIframe').src = iframeSrc;
    });
});


