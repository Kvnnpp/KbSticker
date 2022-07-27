<h1 align="center"> KbSticker to World </h1>


<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>


| 🪧 Vitrine.Dev |     |
| -------------  | --- |
| ✨ Nome        | KbSticker
| 🏷️ Tecnologias | Java, Spring, MongoDB, Heroku, Git
| 🚀 URL         | https://linguagens-imersao-api.herokuapp.com/linguagens
| 🤿 Desafio | https://www.alura.com.br/imersao-java



:hammer: Funcionalidades do projeto

- `Funcionalidade 1`: Acesso a apis para extração de imagens dos filmes e seriados atuais e outros!
- `Funcionalidade 2`: Tratamento da imagem e adição de legenda conforme a nota do filme ou seriado!
- `Funcionalidade 3`: Busca imagens da Nasa para adicionar ao pacote de figurinhas!

## 🤿 5 dias de imersão 
<img src="https://camo.githubusercontent.com/459f141bd5e24c179a0e2dd49691e290ed5c5d4b4cb97767daee7cfaf6e31121/687474703a2f2f696d672e736869656c64732e696f2f7374617469632f76313f6c6162656c3d535441545553266d6573736167653d434f4e434c5549444f26636f6c6f723d475245454e267374796c653d666f722d7468652d6261646765">


## 1º Dia
  - [x] Buscar filmes mais populares
  - [x] Apresentar notas com emoji de estrela
  - [x] Colocar chave da API em um local fora do código fonte

### ✅ Resultado do 1º dia 

![image](https://user-images.githubusercontent.com/82380632/180885187-54d7776b-1be8-4afd-bbb9-052f40209a29.png#vitrinedev)


## 2º Dia
  - [x] Criar diretório de saída das imagens, se ainda não existir.
  - [x] Colocar outra fonte como a Comic Sans ou a Impact, a fonte usada em memes.
  - [x] Tratar as imagens retornadas pela API do IMDB para pegar uma imagem maior ao invés dos thumbnails. Opções: pegar a URL da imagem e remover o trecho mostrado durante a aula ou consumir o endpoint de posters da API do IMDB (mais trabalhoso), tratando o JSON retornado.
  - [x] Fazer com que o texto da figurinha seja personalizado de acordo com as classificações do IMDB.

### ✅ Resultado do 2º dia
![image](https://user-images.githubusercontent.com/82380632/180885393-20db24e1-d55e-4724-89a7-c5aa212ca521.png#vitrinedev)
![image](https://user-images.githubusercontent.com/82380632/180885542-b2f1b933-725a-42f6-8244-7b972774ae52.png#vitrinedev)



## 3º Dia
  - [x] Transformar a classe que representa os conteúdos em um Record, disponível a partir do Java 16
  - [x] consumir outras APIs que contém imagens, como a da Marvel, que é bem diferente. Repositório com APIs públicas
  
  ### ✅ Resultado do 3º dia
  ![image](https://user-images.githubusercontent.com/82380632/180885800-e97d6365-92a6-4c6b-9c01-cb5d793d0c08.png)

   Código refatorado. Foram criadas novas classes, para que a classe App(principal) não fosse uma classe GOD(classe que faz tudo). Foram delegados as tarefas e funções.
   
  
## 4º Dia
  - [x] Criando API com spring
  - [x] Métodos GET, POST implementados nas rotas
  - [x] Conexão com o MongoDB Atlas e aramzenamento dos documentos.
 
 ### ✅ Resultado do 4º dia
 
 Api implementada com o Spring, utilizando o MongoDB para armazenar os dados.

  
 ## 5º Dia
   - [x] Realizar deploy na Heroku
   - [x] link - https://linguagens-imersao-api.herokuapp.com/linguagens

### ✅ Resultado do 5º dia

Stickers para o whatsapp gerado e consumidos a partir da API disponibilizada no HEROKU.
