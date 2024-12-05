

document.querySelectorAll('.excluir').forEach(function(button) {
    button.addEventListener('click', 
    function() {
        if (confirm('Confirma a exclusão?')) {

            const row = this.closest('tr'); 

            const categoriaId = this.dataset.categoriaId;
            
          
            fetch(`/categoria/${categoriaId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.ok) {
                  
                    console.log('Categoria excluída com sucesso.');

                    
                    row.remove();
                } else {
                
                    console.error('Não foi possivel. Existe um equipamento vinculado.');
                    alert('Não foi possivel. Existe um equipamento vinculado.');
                }
            })
            .catch(error => {
                
                console.error('Erro de rede:', error);
                alert('Erro de rede:' + error);
            });
        }
    });
});
