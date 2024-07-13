# Book-Catalog
O Book Catalog é um aplicativo de linha de comando para buscar livros na API Gutendex, registrar em um banco de dados PostgreSQL e buscar livros e autores registrados.

## Funcionalidades

- Buscar livro na API Gutendex.
- Registrar livro no banco de dados.
- Listar todos os livros registrados.
- Listar todos os autores registrados.
- Dado um ano, listar todos os autores registrados que estavam vivo nesse ano.
- Dado um idioma, listar todos os livros registrados disponível nesse idioma.

## Exemplo de uso

Ao executar o aplicativo, você verá um menu como o seguinte:
```bash
1. Cadastrar Livro
2. Listar Todos os Livros Cadastrados
3. Listar Todos os Autores Registrados
4. Listar Autores Vivos em um Determinado Ano
5. Listar Livros em um Determinado Idioma
6. Sair
```
Escolha uma opção e siga as instruções na tela para realizar a operação desejada.

## Tecnologias utilizadas
- Java 17
- Spring boot 3
- Maven
- HttpClient
- Jackson Databind