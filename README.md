# BetaTransporte

<h2><b>Projeto de Engenharia de Software</b></h2>

<p align="justify">A empresa Beta Transportes é uma empresa fictícia adotada como objeto de estudo, com base nesta empresa, foi feito um estudo inicial do problema referente a logistica. A documentação do estudo inicial do problema esta disponível no link <a href="https://docs.google.com/document/d/1z852_M_8ZzeP5U5vLTK-_0BKt8bIZTcqbCnla_5FrP4/edit?usp=sharing"> EIP_00_BetaTransportes </a>.
Com base no estudo inicial do problema, foi realizado uma modelagem do processo em notação BPMN (<i>Business Process Model and Notation</i>) a fim de um melhor entendimento do projeto.
  <h4 align="center">Figura 1: Diagrama BPD Beta Transportes</h4>
<img src="https://i.imgur.com/NS7tkPf.png" height="940" width="940">

Para o desenvolvimento do software Beta Transporte, atualmente a seguinte stack de tecnologias:
<ul>
  <li>JavaFX</li>
  <li>JDK 8</li>
  <li>PostgreSQL</li>
  <li>Java Persistence API v2.1 </li>
  <li>Netbeans IDE 8.2</li>
  <li>Maven</li>
</ul>

<h4 align="left">Idioma</h4>

O projeto é todo escrito em inglês, com excessão de algumas funções e atributos específicos do negócio. Além disto, há um arquivo de internacionalização i18n_pt_BR localizado em "docs/i18N_pt_BR" que descreve a lingua referente ao sistema.

<h4 align="left">Diretórios</h4>

Todo código fonte localiza-se na pasta "src". 

Todas classes referentes as categorias abaixo estão no caminho "src\main\java\com\dev":
<ul>
  <li>VO</li>
  <li>DAO</li>
  <li>Controller</li>
</ul>

Todas classes utilitárias do sistema, localiza-se no pacote "util" em "src\main\java\util".

No diretório "resource", localiza-se os pacotes:
<ul>
  <li>META-INF - Configuração da unidade de persistência</li>
  <li>docs - Documentos e arquivos de configurações</li>
  <li>gui - Interface gráfica do sistema, representado pelos arquivos FXML</li>
   <li>img - Imagens do sistema</li>
   <li>style - Arquivo de folha de estilo do sistema</li>
</ul>

<h4 align="left">Dependências</h4 align="left">

Toda configuração de dependência do sistema é configurado no arquivo "pom.xml".
</p>

