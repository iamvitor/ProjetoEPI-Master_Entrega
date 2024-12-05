// Preencher os campos do modal de atualização com os dados do equipamento
const atualizarModal = document.getElementById('atualizarEquipamentoModal');
atualizarModal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget; // Botão que acionou o modal
    const id = button.getAttribute('data-id'); // ID do equipamento

    // Define a URL do iframe com o ID do equipamento
    const iframeSrc = `/atualizarequipamento/${id}`;
    document.getElementById('atualizarIframe').src = iframeSrc;
});

// Limpar o iframe ao fechar o modal
atualizarModal.addEventListener('hidden.bs.modal', function () {
    const iframe = document.getElementById('atualizarIframe');
    iframe.src = ''; // Limpa o conteúdo do iframe
});

// Preencher o iframe do modal de cadastro com a URL do formulário de cadastro
const cadastrarModal = document.getElementById('cadastrarEquipamentoModal');
cadastrarModal.addEventListener('show.bs.modal', function () {
    const iframeSrc = `/cadastrarequipamento`;
    document.getElementById('cadastrarIframe').src = iframeSrc;
});

// Limpar o iframe do cadastro ao fechar o modal
cadastrarModal.addEventListener('hidden.bs.modal', function () {
    const iframe = document.getElementById('cadastrarIframe');
    iframe.src = ''; // Limpa o conteúdo do iframe
});

// Ouve a mensagem enviada pela página de sucesso no iframe
window.addEventListener('message', function (event) {
    if (event.data === 'fechar-modal') {
        // Fecha o modal de cadastro
        const modal = bootstrap.Modal.getInstance(document.getElementById('cadastrarEquipamentoModal'));
        if (modal) {
            modal.hide(); // Fecha o modal
        }

        // Recarrega a página principal para refletir as alterações
        location.reload();
    }
});

// Preencher os campos do modal de visualização com os dados do equipamento
const visualizarModal = document.getElementById('visualizarEquipamentoModal');
visualizarModal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget; // Botão que acionou o modal
    const id = button.getAttribute('data-id');
    const nome = button.getAttribute('data-nome');
    const categoria = button.getAttribute('data-categoria');

    // Atualiza os campos no modal
    document.getElementById('visualizarId').textContent = id;
    document.getElementById('visualizarNome').textContent = nome;
    document.getElementById('visualizarCategoria').textContent = categoria;
});
