document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', function() {
        // Confirmação antes de excluir
        if (confirm('Confirma a exclusão?')) {

            // Obtém a linha da tabela mais próxima
            const row = this.closest('tr');

            // Obtém o ID do empréstimo a partir do atributo 'data-emprestimo-id'
            const emprestimoId = this.getAttribute('data-emprestimo-id');

            // Faz a requisição para excluir o empréstimo
            fetch(`/emprestimos/excluiremprestimo/${emprestimoId}`, {
                method: 'GET', // Usar GET, já que estamos redirecionando após a exclusão
                headers: {
                    'Content-Type': 'application/json'
                },
            })
                .then(response => {
                    if (response.ok) {
                        // Sucesso: removendo a linha da tabela
                        console.log('Empréstimo excluído com sucesso.');
                        row.remove(); // Remove a linha da tabela
                    } else {
                        // Caso haja algum erro
                        console.error('Erro ao excluir empréstimo.');
                        alert('Erro ao excluir empréstimo.');
                    }
                })
                .catch(error => {
                    // Erro de rede
                    console.error('Erro de rede:', error);
                    alert('Erro de rede: ' + error);
                });
        }
    });
});
