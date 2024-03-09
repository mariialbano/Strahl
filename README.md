# Strahl
API Rest do projeto Strahl - E-commerce para venda de eletrônicos

## Requisitos

- CRUD de Produtos
- CRUD de Usuários
- CRUD de Pedidos
- Autenticação

## Documentação

### Endpoints

- [Listar Produto](#listar-produto)
- [Cadastrar Produto](#cadastra-produto)
- [Apagar Produto](#apagar-produto)
- [Detalhar Produto](#detalhar-produto)
- [Atualizar Produto](#atualizar-produto)


### Listar Produtos

`GET` /produto

Retorna um array com o produto cadastrado.

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Phone S Horizon",
        "preco": 5000,
        "tela": 6,
        "armazenamento": 128,
        "cor": "titânio",
        "camera": 12
    }
]
```

#### Códigos de Resposta

| código | descrição |
|--------|-----------|
|200| Produto retornado com sucesso
|401| Não autorizado. Realize a autenticação em /login
|404| Produto não encontrado

---

### Cadastrar Produto

`POST` /produto

Cadastra um produto.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|--------
|nome|string|✅| Um nome curto para identificar o produto
|preco|int|✅| O preço do produto
|tela|int|✅| O tamanho da tela do produto
|armazenamento|int|✅|Quantidade de armazenamento em Gb do produto
|cor|string|✅| Informa a cor do produto
|camera|int|✅| Informa a qualidade da câmera em Mp


#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Phone S Horizon",
        "preco": 5000,
        "tela": 6,
        "armazenamento": 128,
        "cor": "titânio",
        "camera": 12
    }
]
```


#### Código de Resposta

| código | descrição |
|--------|-----------|
|200| Produto cadastrado com sucesso
|400| Validação falhou. Verifique os dados enviados no corpo da requisição
|401| Não autorizado. Realize a autenticação em /login

---

### Apagar Produto
`DELETE` /produto/`{id}`

Apaga o produto com o `id` informado no parâmetro de path.

#### Código de Resposta

| código | descrição |
|--------|-----------|
|200| Produto apagado com sucesso
|401| Não autorizado. Realize a autenticação em /login

---

### Detalhar Produto

`GET` /produto/`{id}`

Retorna os dados da categoria com o `id` informado no parâmetro de path.


#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Phone S Horizon",
        "preco": 5000,
        "tela": 6,
        "armazenamento": 128,
        "cor": "titânio",
        "camera": 12
    }
]
```

#### Código de Resposta

| código | descrição |
|--------|-----------|
|200| Produto retornado com sucesso
|401| Não autorizado. Realize a autenticação em /login
|404| Não existe produto com o `id` informado

---

### Atualizar Produto

`PUT` /produto/`{id}`

Atualiza os dados do produto com o `id` informado no path


#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|--------
|nome|string|✅| Um nome curto para identificar o produto
|preco|int|✅| O preço do produto
|tela|int|✅| O tamanho da tela do produto
|armazenamento|int|✅|Quantidade de armazenamento em Gb do produto
|cor|string|✅| Informa a cor do produto
|camera|int|✅| Informa a qualidade da câmera em Mp

```js
{
        "id": 1,
        "nome": "Phone S Horizon",
        "preco": 5000,
        "tela": 6,
        "armazenamento": 128,
        "cor": "titânio",
        "camera": 12
}
```

#### Exemplo de Resposta

```js
{
        "id": 1,
        "nome": "Phone S Horizon",
        "preco": 5000,
        "tela": 6,
        "armazenamento": 128,
        "cor": "titânio",
        "camera": 12
}
```


#### Código de Resposta

| código | descrição |
|--------|-----------|
|200| Produto atualizado com sucesso
|400| Validação falhou. Verifique os dados enviados no corpo da requisição
|401| Não autorizado. Realize a autenticação em /login
|404| Não existe produto com o `id` informado

---
