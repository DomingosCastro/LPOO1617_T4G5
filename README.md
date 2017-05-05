# LPOO1617_T4G5
<p>Projeto Final<p>
O tema da aplicação desenvolvida enquadra-se nos jogos de ação, mais especificamente nos jogos de plataforma.
Relativamente aos objetivos secundários, foram selecionados dois, a saber: 
- Física, com recurso ao motor de física box2D;
- Mobile (compatível com Android e com capacidades móveis ou frameworks).
</p>
Descrição do jogo<p>
Esta aplicação consiste num jogo onde a perícia do utilizador é testada no seu limite. Para se concluir o nível com sucesso, o jogador terá de ajudar o Bunny a ultrapassar diversos obstáculos e vencer os seus inimigos num tempo limite. O Bunny corre de forma automática, sendo que o utilizador deve apenas fazê-lo saltar ou “mergulhar” após um salto. Ao longo do seu percurso, o Bunny terá de saltar para várias plataformas coloridas. Sempre que o Bunny salta para uma plataforma, terá de assumir a respetiva cor da plataforma, sendo necessário que o jogador toque na secção do ecrã que induz a mudança de cor. Caso tal não aconteça, o Bunny não se consegue suster na plataforma e cai, perdendo a vida, não sendo concluído o jogo com sucesso. No entanto, ao longo do seu percurso, o Bunny terá ainda a possibilidade de colecionar moedas que estarão sob as plataformas, sendo visível na parte superior do ecrã, o número de moedas colecionadas até ao momento. Esta tarefa não será muito fácil, uma vez que o Bunny irá encontrar no seu caminho v inimigos que vão tentar impedi-lo de atingir o seu objetivo. Os seus inimigos têm comportamentos distintos, o que aumenta o grau de dificuldade do jogo. A colisão com um inimigo leva à redução da vida do Bunny.</p>
No sentido de tornar o Bunny menos vulnerável ao ataque dos seus inimigos, o jogador poderá apanhar diferentes PowerUps, tendo estes diferentes funcionalidades, nomeadamente atribuir ao Bunny um poder defensivo através de um escudo de proteção ou um poder ofensivo através do ataque com projéteis. Além disso, o Bunny poderá ainda apanhar um item temporal, que permitirá dilatar o intervalo de tempo de jogo, e um pacote de vida extra, que restora a vida perdida . O tempo e a vida do Bunny são monitorizados através de uma barra horizontal colorida, visível na parte superior do ecrã.
</p>
</p>
Interface</p>
Main Menu<p>
![main menu](https://cloud.githubusercontent.com/assets/25747718/25767244/864b6d8e-31ef-11e7-8a7e-6f938941c9ea.PNG)
Jogo<p>
![play alternate 405p](https://cloud.githubusercontent.com/assets/25747718/25767242/863a1372-31ef-11e7-8058-8d029edb5889.png)
![play copy alternate 405p](https://cloud.githubusercontent.com/assets/25747718/25767243/864b4a98-31ef-11e7-9568-e4a324499538.png)
Contrlos<p>
![controls](https://cloud.githubusercontent.com/assets/25747718/25767241/861d1484-31ef-11e7-8be4-e4677fd40c0b.png)
</p>
Package Diagram</p>

<p>Class Diagram</p>
![game 2](https://cloud.githubusercontent.com/assets/25747718/25767245/8650de22-31ef-11e7-8d84-31af075ab1b7.png)
<p>Descrição do Class Diagram</p>

<p>Design of behavioural aspects state
(https://cloud.githubusercontent.com/assets/25747718/25767246/865246e0-31ef-11e7-8091-2d3d5e1e8a12.png)
<p>Design Patterns

<p> Unitary Tests
- O botão “Play” efetua a transição do estado “MenuState” para “PlayState”
- Bunny muda de cor de acordo com o input 
- Bunny cai quando a sua cor é diferente da cor da plataforma
- A vida do Bunny acaba cai de uma plataforma
- Bunny não cai quando a sua cor é igual à da plataforma
- Bunny salta, faz salto duplo, e “mergulha” de acordo com o input
- Crawler (Enemy) move-se sozinho sobre a plataforma
- Jumper (Enemy) salta sobre a plataforma
- Os inimigos caem das plataformas
- o Bunny perde vida quando colide com um Enemy
- O Enemy morre quando o Bunny os pisa
  - Os Items de Jogo (Coins, PowerUps, HeatlhPackge, etc) desaparecem do ecrã quando o Bunny os apanha
- Quando estes items são colecionados:
 	- HeatlhPackage: aumenta a vida do Buny
	- ExtraTime: aumenta o tempo de nível disponível 
	- Coins: incrementa o números de Coins colecionadas
	- PowerUps: atualiza o powerup armazenado no HUD
- Ativar PowerUp armazenado, de acordo com input: 
- Shield: imune à colisão com inimigos
- Bullet: é lançado um projétil 
- Projétil elimina o inimigo quando o atinge  
- Transição para o estado “GameOverState” quando a vida do Bunny acaba.
- Transição para WinState quando o Tempo acaba
- Numero de coins é armazenado nos Highscores aquando da transição para WinState



