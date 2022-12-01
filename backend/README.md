<h1 align="center">
  💻<br>RestAPI Spring Boot
</h1>

## 🗃️ Documentos Projeto:

<details>
<summary><b>📒 Entidades</b></summary>

### 1. Usuário
- **Características do Usuário [Cliente/ADM/Editor/Vendedor]:**
    - Nome
    - Sobrenome
    - E-mail (Único)
    - Senha
    - CPF/CNPJ (Único)
    - RG/IE (Único e opcional)
    - Gênero
    - Data de Nascimento
    - Apelido Endereço
        - Endereço: CEP, Bairro, Logradouro, Número, Complemento, Referência, Ativo (selecionado)
    - Ativo (Cadastro Ativo)
    - Imagem

### 2. Produto
- **Características do Produto:**
    - Nome
    - Descrição (curta)
    - Detalhes (longa)
    - Imagem
    - Estoque
    - Peso
    - Dimensões: Comprimento, Largura, Altura
    - Gênero (masculino/feminino/menino/menina/unissex)
    - Categoria
    - Tipo
    - Tamanho
    - Marca
    - Cor
    - Preço
    - Oferta (Porcentagem)
    - Ativo

- **Características do Pedido:**
    - N produtos
    - Subtotal
    - Taxa da entrega
    - Valor total
    - Data do pedido
    - Data de pagamento
    - Data do envio
    - Data da entrega
    - Status: AGUARDANDO PAGAMENTO, PAGAMENTO CONFIRMADO, ENVIADO, ENTREGUE

- **Características do Gênero**
    - Descrição

- **Características da Categoria**
    - Descrição

- **Características do Tipo**
    - Descrição

- **Características do Tamanho**
    - Descrição

- **Características da Marca**
    - Descrição
    - Imagem

- **Características da Cor**
    - Descrição

</details>

<details>
<summary><b>📝 Modelagem de Entidades</b></summary>

<details>
<summary><b>🧺 Produto</b></summary>

![Produto Conceitual](assets/images/produto-conceitual.png)

</details>

<details>
<summary><b>👦 Usuário</b></summary>

![Produto Conceitual](assets/images/usuario-conceitual.png)

</details>

</details>


<details>
<summary><b>⚙️ Configurações</b></summary>

- **MySQL Ignore Case**
    - SELECT * FROM table WHERE field LIKE '%texto_para_encontrar%' COLLATE utf8_general_ci

- **PostgreSQL Time and TimeZone**
    - SHOW TIMEZONE;
    - SET TIME ZONE 'UTC';
    - SET TIME ZONE 'GMT+3';
    - SELECT CURRENT_TIMESTAMP
    - SELECT NOW()

</details>

<details>
<summary><b>🧑‍⚖️ Hierarquia Dependências</b></summary>

- **Sprint Básico Funcionamento Usuário**
    - 1. Página Inicial
    - 2. Página Filtro
    - 3. Página Produto Individual
    - 4. Página Autenticação (Cliente)
    - 5. Página Carrinho (Cliente)
    - 6. Página Pagamento (Cliente)
    - 7. Página Conta (Cliente)
    - 8. Sobre
    - 9. Políticas de Privacidade

- **Sprint Melhor Experiência Usuário**
    - 1. Página Produtos
    - 2. Favoritos
    - 3. Contato
    - 4. Não Encontrada
    - 5. Comentário

- **Experiência ADM**
    - 1. Login
    - 2. Cadastro Editor/Vendedor
    - 3. Editar Pefil
    - 4. Página E-commerce -> Cadastros (Produto)
    - 5. Página Inicial (Relatórios)

- **Acompanhamento Cliente-Vendedores**
    - 1. Pedidos
    - 2. Comentários
    - 3. Chat

- **Geração de Conteúdo (SEO)**
    - 1. Blog

- **Gestão de campanhas**
    - 1. Inicial
    - 2. Produtos
    - 3. Newsletter

</details>

## 📌 Roadmap (Sprints):

<details>
<summary><b>🚀 Funcionamento Usuário</b></summary>

### Backlog Funcionamento Usuário

- [x] Design (Figma)
    - [x] Página Inicial
    - [x] Página Filtro
    - [x] Página Produto Individual
    - [x] Página Autenticação (Cliente)
    - [x] Página Carrinho (Cliente)
    - [x] Página Pagamento (Cliente)
    - [x] Página Conta (Cliente)
    - [x] Sobre
    - [x] Políticas de Privacidade
- [x] Front-End (Angular)
    - [x] Página Inicial (Header - Hero - Footer)
    - [x] Página Filtro
    - [x] Página Produto Individual
    - [x] Página Autenticação (Cliente)
    - [x] Página Carrinho (Cliente)
    - [x] Página Pagamento (Cliente)
    - [x] Página Conta (Cliente)
    - [x] Sobre
    - [x] Políticas de Privacidade
- [x] Back-End (RESTApi Spring)
    - [x] Página Filtro
    - [x] Página Produto Individual
    - [x] Página Autenticação (Cliente)
    - [x] Página Carrinho (Cliente)
    - [x] Página Pagamento (Cliente)
    - [x] Página Conta (Cliente)

### Sprint Planning Funcionamento Usuário [1 2 3 5 8]
Task      | Score | Responsável
----------|-------|------------
Design    | 3     | Higor
Front-End | 5     | Higor
Back-End  | 3     | Higor

<details>
<summary><b>⏳ Sprint Funcionamento Usuário</b></summary>

- ### Prazos
    - Design (7 dias)
    - Back-End (7 dias)
    - Front-End (7 dias)

- ### Em Andamento
    - [x] 28/10 Design
    - [x] 04/11 Back-End
    - [x] 11/11 Front-End

- ### Validação
    - [x] 29/10 Design
    - [x] 06/11 Back-End
    - [x] 16/11 Front-End

- ### Aguardando Deploy
    - [ ] 01/12

- ### Em Produção

- ### Revisão Sprint

</details>

</details>

<details>
<summary><b>🤗 Experiência Usuário</b></summary>

### Backlog Experiência Usuário

- [x] Design (Figma)
    - [x] Página Produtos
    - [ ] Favoritos
    - [x] Contato
    - [x] Não Encontrada
    - [ ] Comentário
- [x] Front-End (Angular)
    - [x] Página Produtos
    - [ ] Favoritos
    - [x] Contato
    - [x] Não Encontrada
    - [ ] Comentário
- [ ] Back-End (RESTApi Spring)
    - [ ] Favoritos
    - [ ] Comentário

### Sprint Planning Experiência Usuário [1 2 3 5 8]
Task      | Score | Responsável
----------|-------|------------
Design    | 4     | Higor
Front-End | 5     | Higor

<details>
<summary><b>⏳ Sprint Experiência Usuário</b></summary>

- ### Prazos
    - Design (7 dias)
    - Back-End (7 dias)

- ### Em Andamento
    - [ ] xx/xx Design
    - [ ] xx/xx Front-End

- ### Validação
    - [ ] xx/xx Design
    - [ ] xx/xx Front-End

- ### Aguardando Deploy
    - [ ] xx/xx

- ### Em Produção

- ### Revisão Sprint

</details>

</details>

<details>
<summary><b>🚧 Experiência ADM</b></summary>

### Backlog Experiência ADM

- [ ] Design (Figma)
    - [ ] Login
    - [ ] Cadastro Editor/Vendedor
    - [ ] Editar Pefil
    - [ ] Página E-commerce (Produto) -> Cadastrar/Editar (Produto)
    - [ ] Página Inicial (Relatórios)
- [ ] Front-End (Angular)
    - [ ] Login
    - [ ] Cadastro Editor/Vendedor
    - [ ] Editar Pefil
    - [ ] Página E-commerce (Produto) -> Cadastrar/Editar (Produto)
    - [ ] Página Inicial (Relatórios)
- [ ] Back-End (RESTApi Spring)
    - [ ] Login
    - [ ] Cadastro Editor/Vendedor
    - [ ] Editar Pefil
    - [ ] Página E-commerce (Produto) -> Cadastrar/Editar (Produto)
    - [ ] Página Inicial (Relatórios)

### Sprint Planning Experiência ADM [1 2 3 5 8]
Task      | Score | Responsável
----------|-------|------------
Design    | 5     | Higor
Front-End | 5     | Higor
Back-End  | 5     | Higor

<details>
<summary><b>⏳ Sprint Experiência ADM</b></summary>

- ### Prazos
    - Design (7 dias)
    - Front-End (7 dias)
    - Back-End (7 dias)

- ### Em Andamento
    - [ ] xx/xx Design
    - [ ] xx/xx Front-End
    - [ ] xx/xx Back-End

- ### Validação
    - [ ] xx/xx Design
    - [ ] xx/xx Front-End
    - [ ] xx/xx Back-End

- ### Aguardando Deploy
    - [ ] xx/xx

- ### Em Produção

- ### Revisão Sprint

</details>

</details>

<details>
<summary><b>🚛 Cliente-Vendedores</b></summary>

### Backlog Cliente-Vendedores

- [ ] Design (Figma)
    - [ ] Pedidos
    - [ ] Comentários
    - [ ] Chat
- [ ] Front-End (Angular)
    - [ ] Pedidos
    - [ ] Comentários
    - [ ] Chat
- [ ] Back-End (RESTApi Spring)
    - [ ] Pedidos
    - [ ] Comentários
    - [ ] Chat

### Sprint Planning Cliente-Vendedores [1 2 3 5 8]
Task      | Score | Responsável
----------|-------|------------
Design    | 5     | Higor
Front-End | 5     | Higor
Back-End  | 8     | Higor

<details>
<summary><b>⏳ Sprint Cliente-Vendedores</b></summary>

- ### Prazos
    - Design (7 dias)
    - Front-End (7 dias)
    - Back-End (7 dias)

- ### Em Andamento
    - [ ] xx/xx Design
    - [ ] xx/xx Front-End
    - [ ] xx/xx Back-End

- ### Validação
    - [ ] xx/xx Design
    - [ ] xx/xx Front-End
    - [ ] xx/xx Back-End

- ### Aguardando Deploy
    - [ ] xx/xx

- ### Em Produção

- ### Revisão Sprint

</details>

</details>