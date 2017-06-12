# LPOO1617_T4G5
<p>
<b>Projeto Final</b>
<p>
O tema da aplicação desenvolvida enquadra-se nos jogos de ação, mais especificamente nos jogos de plataforma.
Relativamente aos objetivos secundários, foram selecionados dois, a saber: 
- Física, com recurso ao motor de física box2D;
- Mobile (compatível com Android e com capacidades móveis ou frameworks).
</p>
</p>
<b>Descrição do jogo</b><p>
Esta aplicação consiste num jogo onde a perícia do utilizador é testada no seu limite. Para se concluir o nível com sucesso, o jogador terá de ajudar o Bunny a ultrapassar diversos obstáculos e vencer os seus inimigos num tempo limite. O Bunny corre de forma automática, sendo que o utilizador deve apenas fazê-lo saltar ou “mergulhar” após um salto. Ao longo do seu percurso, o Bunny terá de saltar para várias plataformas coloridas. Sempre que o Bunny salta para uma plataforma, terá de assumir a respetiva cor da plataforma, sendo necessário que o jogador toque na secção do ecrã que induz a mudança de cor. Caso tal não aconteça, o Bunny não se consegue suster na plataforma e cai, perdendo a vida, não sendo concluído o jogo com sucesso. No entanto, ao longo do seu percurso, o Bunny terá ainda a possibilidade de colecionar moedas que estarão sob as plataformas, sendo visível na parte superior do ecrã, o número de moedas colecionadas até ao momento. Esta tarefa não será muito fácil, uma vez que o Bunny irá encontrar no seu caminho v inimigos que vão tentar impedi-lo de atingir o seu objetivo. Os seus inimigos têm comportamentos distintos, o que aumenta o grau de dificuldade do jogo. A colisão com um inimigo leva à redução da vida do Bunny.</p>
No sentido de tornar o Bunny menos vulnerável ao ataque dos seus inimigos, o jogador poderá apanhar diferentes PowerUps, tendo estes diferentes funcionalidades, nomeadamente atribuir ao Bunny um poder defensivo através de um escudo de proteção ou um poder ofensivo através do ataque com projéteis. Além disso, o Bunny poderá ainda apanhar um item temporal, que permitirá dilatar o intervalo de tempo de jogo, e um pacote de vida extra, que restora a vida perdida . O tempo e a vida do Bunny são monitorizados através de uma barra horizontal colorida, visível na parte superior do ecrã.
</p>
</p>
<b>Interface</b></p>
Main Menu<p>
![main menu](https://cloud.githubusercontent.com/assets/25747718/25767772/42724e16-31f4-11e7-8a7e-09a66118d1d0.PNG)
</p>Jogo<p>
![play alternate 405p](https://cloud.githubusercontent.com/assets/25747718/25767769/425c59e4-31f4-11e7-8636-8e633fbcfac1.png)<p>
![play copy alternate 405p](https://cloud.githubusercontent.com/assets/25747718/25767773/42764eee-31f4-11e7-88d2-a5acf6d5cbd8.png)
</p>Controlos<p>
![controls](https://cloud.githubusercontent.com/assets/25747718/25767768/424176e2-31f4-11e7-8ace-f5e5ae3fc2f9.png)
</p>
</p>
<b>Package Diagram</b><p>
![untitled diagram 1](https://cloud.githubusercontent.com/assets/25747718/25767778/53a170f4-31f4-11e7-8bc6-3961d370da60.png)<p>
Atualizado:
- Os packages foram estruturados segundo a arquitetura MVC implementada<p>

![My image](https://github.com/DomingosCastro/LPOO1617_T4G5/blob/Final-Project/update.JPG)

</p>
</p><b>Class Diagram</b><p>
![game 2](https://cloud.githubusercontent.com/assets/25747718/25767770/427132ec-31f4-11e7-90e1-d6cc5e862e03.png)
</p>Descrição do Class Diagram: [classes.pdf](https://github.com/DomingosCastro/LPOO1617_T4G5/files/980519/classes.pdf)<p>

Atualizado:
![My image](https://github.com/DomingosCastro/LPOO1617_T4G5/blob/Final-Project/update1.JPG)
</p>
</p><b>Design of behavioural aspects state</b><p>
![design of behavioural aspects state](https://cloud.githubusercontent.com/assets/25747718/25767771/427199c6-31f4-11e7-81ec-42d167d1ce7e.png)
<p> 
<p>
<p>
</p><b>Design Patterns</b></p>
Singleton: Garantir uma única instancia de GameStateManager de acesso global</p>
Strategy: Diferentes tipos de inimigos que executam diferentes ações (ex: JUMPING, FLYING, etc). Cada tipo de inimigo pode executá-las usando um algoritmo (strategy) diferente</p>
State: As personagens podem ter diferentes estados que limitam as ações (ex: JUMPING, DOUBLE JUMPING, DIVING , etc) que podem ser realizadas em cada momento do jogo. Por exemplo, a ação “salto duplo” só está disponível se o estado atual da personagem for JUMPING.
<p>
<p>
<p>
<b> Unitary Tests</b><p>
- O botão “Play” efetua a transição do estado “MenuState” para “PlayState”<p>
- Bunny muda de cor de acordo com o input <p>
- Bunny cai quando a sua cor é diferente da cor da plataforma<p>
- A vida do Bunny acaba cai de uma plataforma<p>
- Bunny não cai quando a sua cor é igual à da plataforma<p>
- Bunny salta, faz salto duplo, e “mergulha” de acordo com o input<p>
- Crawler (Enemy) move-se sozinho sobre a plataforma<p>
- Jumper (Enemy) salta sobre a plataforma<p>
- Os inimigos caem das plataformas<p>
- o Bunny perde vida quando colide com um Enemy<p>
- O Enemy morre quando o Bunny os pisa<p>
  - Os Items de Jogo (Coins, PowerUps, HeatlhPackge, etc) desaparecem do ecrã quando o Bunny os apanha<p>
- Quando estes items são colecionados:<p>
 	- HeatlhPackage: aumenta a vida do Buny<p>
	- ExtraTime: aumenta o tempo de nível disponível <p>
	- Coins: incrementa o números de Coins colecionadas<p>
	- PowerUps: atualiza o powerup armazenado no HUD<p>
- Ativar PowerUp armazenado, de acordo com input: <p>
- Shield: imune à colisão com inimigos<p>
- Bullet: é lançado um projétil <p>
- Projétil elimina o inimigo quando o atinge  <p>
- Transição para o estado “GameOverState” quando a vida do Bunny acaba.<p>
- Transição para WinState quando o Tempo acaba<p>
- Numero de coins é armazenado nos Highscores aquando da transição para WinState<p>

</p><b>Instruções de instalação</b><p>

</p><b>Desktop</b><p>
- Download do Repositório neste branch e descomprimir<p>
- Abrir Android Studio e importar o projeto respetivo (Gradle, entre outros)<p>
- Correr no modo Desktop

</p><b>Android</b><p>
- Download do ficheiro .apk<p>
- Instalar 



