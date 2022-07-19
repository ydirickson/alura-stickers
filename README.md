<h1 id="titulo" style="text-align: center">YDIRICKSON - Alura Sticker</h1>

Repositório com códigos da #ImersãoJava da Alura

<div id="badges">
    <img src="https://img.shields.io/github/license/ydirickson/alura-stickers?style=for-the-badge" alt="Insígnea de Licença GPL" />
    <img src="https://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=YELLOW&style=for-the-badge"
    alt="Insígnea de Status em Desenvolvimento" />
</div>

## Índice

 * [Título e Capa](#titulo)
 * [Badges](#badges)
 * [Índice](#índice)
 * [Sobre](#sobre)
 * [Aulas](#aulas)
 * [Dependências](#dependências)
 * [Tecnologias Utilizadas](#tecnologias-utilizadas)
 * [Como Executar](#como-executar)
 * [Autores](#autores)
 * [Licença](#licença)

## Sobre

Esse repositório conteŕa os meus códigos da #ImersãoJava da Alura, feita de 18/07/2022 até 22/07/2022. O conteúdo visto em cada aula estará descrito em [Aulas](#aulas) e estará separado em branches específicas, com uma branch com a finalização do código.

## Aulas

As aulas da #ImersãoJava foram separadas da seguinte maneira:

- [Aula 01](/github/ydirickson/tree/Aula01) (18/07/2022): Criado um projeto simples de Java (sem Build Tools) pelo VSCode e foi consumida a primeira API do IMDB durante a aula. Os desafios implementados foram: 
    - Consumir outros endpoints como _filmes mais populares_, _melhores séries_ e _sérias mais populares_(:ballot_box_with_check:);
    - Usar emojis, cores e formatação nos prints do console (:ballot_box_with_check:);
    - Colocar a chave de API do IMDB em outro lugar, seja variável de ambiente ou arquivo properties (:ballot_box_with_check:);
    - Mudar o JsonParser para alguma biblioteca JSON como Jackson ou GSON (:ballot_box_with_check:);
    - *Desafio Supremo* criar uma maneira de o usuário dar uma avaliação ao filme, puxando algum arquivo de configuração OU pedindo a avaliação para o usuário digitar no terminal.

## Dependências

O projeto atual utiliza as seguintes dependências (presentes na pasta /bin):

 - `jackson-annotations-2.13.3.jar`
 - `jackson-core-2.13.3.jar`
 - `jackson-databind-2.13.3.jar`

##  Tecnologias Utilizadas

- ``Java 17``
- ``VSCode``

## Como Executar

Recomenda-se abrir o projeto dentro do VSCode (ou outra IDE) para usar o executor de projetos Java. Ao se executar pela primeira vez na sessão do usuário, é necessário exportar a chave de API do IMDB.

No Linux/Unix/Mac:
```
export IMDB_API_KEY=<SUA_CHAVE_API>
```

No Windows:
```
setx IMDB_API_KEY="<SUA_CHAVE_API>"
```

## Autores

| [<img src="https://avatars.githubusercontent.com/u/4514095?v=4" width=115><br><sub>Yuri Dirickson</sub>](https://github.com/ydirickson) |
| :---: |

## Licença

Esse projeto está licenciado sobre a [GPL 3.0](./LICENSE)