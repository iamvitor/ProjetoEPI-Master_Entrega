

document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', 
    function() {
        if (confirm('Confirma a exclusão?')) {

            const row = this.closest('tr'); 

            const equipamentoId = this.dataset.equipamentoId;
            
          
            fetch(`/equipamento/${equipamentoId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.ok) {
                  
                    console.log('Equipamento excluído com sucesso.');

                    
                    row.remove();
                } else {
                
                    console.error('Não foi possivel. Equipamento esta no uso!');
                    alert('Não foi possivel. Equipamento esta no uso!');
                }
            })
            .catch(error => {
                
                console.error('Erro de rede:', error);
                alert('Erro de rede:' + error);
            });
        }
    });
});
