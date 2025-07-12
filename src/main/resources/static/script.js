console.log("Oie");

document.addEventListener('DOMContentLoaded', function () {

    const imagensPorProduto = [
        // Produto 0: Blusa Térmica
        [
            'https://cdn.awsli.com.br/600x700/992/992433/produto/46349555/9f8691d9a1.jpg',
            'https://i.pinimg.com/736x/b9/93/ce/b993ce86ea664a0fb67d8fbdfd7fe436.jpg',
            'https://i.pinimg.com/736x/4d/bb/28/4dbb28713d3217ae6479280ea84603ee.jpg'
        ],
        // Produto 1: Legging Para Academia
        [
            'https://http2.mlstatic.com/D_NQ_NP_951699-MLB84463432898_052025-O-calca-legging-feminina-fitness-academia-poliamida.webp',
            'https://i.pinimg.com/736x/a1/2b/3c/a12b3c4d5e6f7a8b9c0d1e2f3a4b5c6d.jpg',
            'https://i.pinimg.com/736x/e5/f6/a7/e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0.jpg'
        ],
        // Produto 2: Caneta Luxo Rosa
        [
            'https://i.pinimg.com/736x/2d/6f/25/2d6f2577d06a9e2565975b0e8966c229.jpg',
            'https://i.pinimg.com/736x/c3/d4/e5/c3d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8.jpg',
            'https://i.pinimg.com/736x/f7/a8/b9/f7a8b9c0d1e2f3a4b5c6d7e8f9a0b1c2.jpg'
        ],
        // Produto 3: Agenda Máfia Rosa
        [
            'https://i.pinimg.com/736x/8b/4c/2a/8b4c2a9f5e3d1b7c8a9e6f4d2c1a5b8e.jpg',
            'https://i.pinimg.com/736x/d3/e4/f5/d3e4f5a6b7c8d9e0f1a2b3c4d5e6f7a8.jpg',
            'https://i.pinimg.com/736x/b9/c0/d1/b9c0d1e2f3a4b5c6d7e8f9a0b1c2d3e4.jpg'
        ],
        // Produto 4: Mousepad Gamer
        [
            'https://i.pinimg.com/736x/5c/8d/1f/5c8d1f3e7b9a2c4d6e8f1a3b5c7d9e2f.jpg',
            'https://i.pinimg.com/736x/e7/f8/a9/e7f8a9b0c1d2e3f4a5b6c7d8e9f0a1b2.jpg',
            'https://i.pinimg.com/736x/c5/d6/e7/c5d6e7f8a9b0c1d2e3f4a5b6c7d8e9f0.jpg'
        ],
        // Produto 5: Chaveiro Exclusivo
        [
            'https://i.pinimg.com/736x/9e/2f/4a/9e2f4a6b8c1d3e5f7a9b2c4d6e8f1a3b.jpg',
            'https://i.pinimg.com/736x/f1/a2/b3/f1a2b3c4d5e6f7a8b9c0d1e2f3a4b5c6.jpg',
            'https://i.pinimg.com/736x/a7/b8/c9/a7b8c9d0e1f2a3b4c5d6e7f8a9b0c1d2.jpg'
        ],
        // Produto 6: Pack de Adesivos
        [
            'https://i.pinimg.com/736x/3b/7d/9e/3b7d9e1f4a6c8b2d5e7f9a1c3b5d7e9f.jpg',
            'https://i.pinimg.com/736x/d9/e0/f1/d9e0f1a2b3c4d5e6f7a8b9c0d1e2f3a4.jpg',
            'https://i.pinimg.com/736x/b5/c6/d7/b5c6d7e8f9a0b1c2d3e4f5a6b7c8d9e0.jpg'
        ],
        // Produto 7: Ecobag Sustentável
        [
            'https://i.pinimg.com/736x/7f/1a/3c/7f1a3c5e8b9d2f4a6c8e1b3d5f7a9c2e.jpg',
            'https://i.pinimg.com/736x/e3/f4/a5/e3f4a5b6c7d8e9f0a1b2c3d4e5f6a7b8.jpg',
            'https://i.pinimg.com/736x/c1/d2/e3/c1d2e3f4a5b6c7d8e9f0a1b2c3d4e5f6.jpg'
        ],
        // Produto 8: Popsocket Rosa
        [
            'https://i.pinimg.com/736x/2e/6f/8a/2e6f8a4c1d5b7e9f2a4c6e8f1a3b5d7e.jpg',
            'https://i.pinimg.com/736x/f5/a6/b7/f5a6b7c8d9e0f1a2b3c4d5e6f7a8b9c0.jpg',
            'https://i.pinimg.com/736x/a9/b0/c1/a9b0c1d2e3f4a5b6c7d8e9f0a1b2c3d4.jpg'
        ]
    ];

    // Selecionando todos os carrosséis
    const carrosseis = document.querySelectorAll('.carrossel');

    // Índices atuais para cada carrossel
    let indicesAtuais = new Array(carrosseis.length).fill(0);

    // Função para trocar imagem
    function trocarImagem(indiceProduto, direcao) {
        const imagensAtual = imagensPorProduto[indiceProduto];

        if (!imagensAtual || imagensAtual.length === 0) {
            return; // Se não há imagens definidas para este produto, não faz nada
        }

        if (direcao === 'proximo') {
            indicesAtuais[indiceProduto] = (indicesAtuais[indiceProduto] + 1) % imagensAtual.length;
        } else {
            indicesAtuais[indiceProduto] = (indicesAtuais[indiceProduto] - 1 + imagensAtual.length) % imagensAtual.length;
        }

        const imagemElemento = carrosseis[indiceProduto].querySelector('img');
        if (imagemElemento) {
            imagemElemento.src = imagensAtual[indicesAtuais[indiceProduto]];
        }
    }

    // Adicionando event listeners para cada carrossel
    carrosseis.forEach((carrossel, indice) => {
        const botaoAnterior = carrossel.querySelector('.btn-anterior');
        const botaoProximo = carrossel.querySelector('.btn-proximo');

        if (botaoAnterior) {
            botaoAnterior.addEventListener('click', () => {
                trocarImagem(indice, 'anterior');
            });
        }

        if (botaoProximo) {
            botaoProximo.addEventListener('click', () => {
                trocarImagem(indice, 'proximo');
            });
        }
    });

    // Função para adicionar efeito de transição suave
    const imagensCarrossel = document.querySelectorAll('.carrossel img');
    imagensCarrossel.forEach(img => {
        img.style.transition = 'opacity 0.3s ease-in-out';

        img.addEventListener('load', function () {
            this.style.opacity = '1';
        });
    });
});