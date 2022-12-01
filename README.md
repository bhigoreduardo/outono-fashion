<h1 align="center">
  💻<br>Outono Fashion E-commerce
</h1>
<img src="./assets/images/cover.png" alt="Cover projeto" />

- **Cliente:** Higor E. Batista
- **PO (Product Owner):** Higor E. Batista
- **Scrum Master:** Higor E. Batista
- **Dev Team:** Higor E. Batista

✅ RESTApi
✅ Design Responsivo
<!-- ✅ Segurança e Autenticação JWT -->
<!-- ✅ Gateway Pagamento -->
<!-- ✅ CMS (Dashboard Estatísticas e Gestão) -->

# ✨ Objetivo:

- **Necessidade:** Aplicação web (E-commerce) para facilitar compra de moda e vestuário
- **Público-Alvo:** Jovens estilosos conectados à rede

<details>
<summary><b>📒 Regras de Negócio</b></summary>

## Requisitos:
### 1. Usuário
- **Experiência do Usuário (Cliente):**
    - Autenticação para acessar a conta, comentar, favoritar, pagar/comprar (segurança)
    - Buscar e filtrar produtos limitando 12 produtos/página (avançada)
    - Cadastrar diferentes endereços de entrega (apelido)
    - Comentar em produtos comprados (credibilidade)
    - Permitir cadastrar somente um CPF/CNPJ e E-mail
    - Pagamento facilitado e flexível no cartão de crédito em até 12x sem juros
    - Pagamento cartão, boleto e PIX
    - Cupom de desconto restrito por CPF/CNPJ não cumulativo e com validade
    - Salvar cartões de crédito histórico
    - Visualização prévia dos detalhes de produtos (modal)
    - Devolução no cartão ou conta bancária do cliente

- **Experiência do Usuário (Administrador):**
    - Acessar todas compras, faturamento, chat
    - Cadastrar produto e usuário
    - Editar produto e usuário
    - Definir nível de acesso usuário
    - Liberação de cadastro de usuário (vendedor)
    - Cadastrar e Editar postagens Blog (todos)

- **Experiencia do Usuário (Vendedor):**
    - Conversar com cliente em chat
    - Buscar, acompanhar e editar status pedido
    - Editar próprias informações cadastrais
    - Cadastrar e Editar próprias postagens Blog

### 2 Produto
- **Valores do Gênero**
    - Masculino, Feminino, Menino, Menina, Unissex

- **Valores da Categoria**
    - Calçados, Roupas, Acessórios
    
- **Valores do Tipo**
    - *Calçados:* Botas, Chinelos, Chuteiras(M), Crocs, Sapatênis, Tênis
    - *Roupas:* Bermudas, Calças, Camisas, Jaquetas, Moletons, Shorts, Vestidos(F)
    - *Acessórios:* Bonés, Malas, Meias, Mochilas, Óculos, Relógios

- **Valores do Tamanho**
    - PP, P, M, G, GG, XG, 20 - 50 (múltiplo 2), Único

- **Valores da Marca**
    - *Calçados:* Adidas, Asics, Fila, Kappa, Mizuno, Nike, Oakley, Olympikus, Puma
    - *Roupas:* Adidas, Aramis, Armadilho, Calvin Klein, Colcci, Everlast, Lacoste, Nike, Reserva
    - *Acessórios:* Adidas, Amora, Capricho, Caterpillar, Coca Cola, Colcci, Everlast

- **Valores da Cor**
    - Amarelo, Azul Claro, Azul Escuro, Bege, Bordô, Branco, Cinza, Dourado, Laranja, Lilás, Marinho, Marrom, Preto, Rosa, Roxo
    - Verde, Verde Escuro, Verde Claro, Vermelho, Vinho, Violeta

### 3. Blog
- **Características do Post (Blog)**
    - Título
    - Conteúdo (Text Rich)
    - Imagem Capa
    - Autor
    - Data Postagem
    - Categoria
    - Tags

</details>

<details>
<summary><b>📃 Rotas</b></summary>

### 1. Produtos
- **Página Produtos:** Dedicada ao Gênero dos produtos
    - Página Produtos Masculino: `/masculino`
    - Página Produtos Feminino: `/feminino`
    - Página Produtos Menino: `/menino`
    - Página Produtos Menina: `/menina`

- **Página Filtro:** Dedicada ao resultado de busca do usuário (cliente)
    - Página Filtro Produto Individual: `/produtos/camisa-polo-masculina/1`
    - Página Filtro Pesquisa (Barra de Pesquisa): `/produtos/bota-masculina-colcci-preta-tamanho-40`
    - Página Filtro (Sidebar e Navbar): `/produtos?categoria=calcados&tipo=chinelo&genero=masculino&tamanho=40&tamanho42&marca=coca-cola&cor=azul&precoMin=60&precoMax=100`

    - Sorted: Mais Populares, Novidades, Menor Preço, Maior Preço, Maior Desconto
    
### 2. Usuários (Cliente)
- **Página Autenticação:** Dedicada a permissão de acesso ao usuário (cliente)
    - Página Login/Cadastro: `/login`
    - Página Cadastro Confirmação: `/login/confirmacao`
    - Página Login Finalizar compra: `/login/finalizar-compra`
    - Página Recuperar Senha: `/login/recuperar-senha`

- **Página Dados Cliente**
    - Página Conta: `/conta`
    - Página Favorito: `/conta/favoritos`

- **Página Itens Compra**
    - Página Carrinho: `/carrinho`

- **Página Pagamento**
    - Página Finalizar Compra: `/finalizar-compra`
    - Página Confirmação: `/finalizar-compra/confirmacao`

### 3. Navegação Livre
- **Página Institucionais:** Dedicada a exploração do usuário (cliente)
    - Página Inicial: `/inicio`
    - Página Sobre: `/sobre`
    - Página Contato: `/contato`
    - Página Blog: `/blog`
    - Página Não Encontrada: `**`

### 4. Autenticação Gestores
- **Página Gerência Usuário (Comum)**
    - Página Login/Cadastro CMS: `/cms-login`
    - Página Inicial CMS: `/cms`
    - Página Chat Clientes Online: `/cms/chat`
    - Página Perfil (Visualizar): `/cms/perfil`
        - Editar: `/cms/perfil?id=1`

- **Página Gerência Usuário (ADM)**
    - Página Usuários (Editores): `/cms/editores`
        - Visualizar: `/cms/editores?id=1`
        - Cadastrar: `/cms/editores/cadastrar`
        - Editar: `/cms/editores/editar?id=1`
    - Página Usuários (Vendedores): `cms/vendedores`
        - Visualizar: `/cms/vendedores?id=1`
        - Cadastrar: `/cms/vendedores/cadastrar`
        - Editar: `/cms/vendedores/editar?id=1`

- **Página Gerência Usuário (Editor/Vendedor)**
    - Página Cadastro Confirmação CMS: `/cms-login/cadastrado`

### 5. Gestão do Produto
- **Página Gerência Usuário (Comum)**
    - Página E-commerce Gênero (Listar): `/cms/produto-generos`
    - Página E-commerce Categoria (Listar): `/cms/produto-categorias`
    - Página E-commerce Tipo (Listar): `/cms/produto-tipos`
    - Página E-commerce Tamanho (Listar): `/cms/produto-tamanhos`
    - Página E-commerce Marca (Listar): `/cms/produto-marcas`
    - Página E-commerce Cor (Listar): `/cms/produto-cores`
    - Página E-commerce Produto (Listar): `/cms/produtos`
        - Visualizar: `/cms/produtos?id=1`
    - Página Pedidos (Listar): `cms/pedidos`
        - Visualizar: `/cms/pedidos?id=1`
        - Editar: `/cms/pedidos/editar?id=1`
    - Página Comentários Produto (Listar): `cms/comentarios`
        - Visualizar: `/cms/comentarios?id=1`

- **Página Gerência Usuário (ADM/Editor)**
    - Produto Gênero:
        - Cadastrar: `/cms/produto-generos/cadastrar`
        - Editar: `/cms/produto-generos/editar?id=1`
    - Produto Categoria:
        - Cadastrar: `/cms/produto-categorias/cadastrar`
        - Editar: `/cms/produto-categorias/editar?id=1`
    - Produto Tipo:
        - Cadastrar: `/cms/produto-tipos/cadastrar`
        - Editar: `/cms/produto-tipos/editar?id=1`
    - Produto Tamanho:
        - Cadastrar: `/cms/produto-tamanhos/cadastrar`
        - Editar: `/cms/produto-tamanhos/editar?id=1`
    - Produto Marca:
        - Cadastrar: `/cms/produto-marcas/cadastrar`
        - Editar: `/cms/produto-marcas/editar?id=1`
    - Produto Cor:
        - Cadastrar: `/cms/produto-cores/cadastrar`
        - Editar: `/cms/produto-cores/editar?id=1`
    - Produto:
        - Cadastrar: `/cms/produtos/cadastrar`
        - Editar: `/cms/produtos/editar?id=1`

### 6. Gestão do Blog
- **Página Gerência Usuário (Comum)**
    - Página Post Blog (Listar): `cms/posts`
        - Cadastrar: `/cms/posts/cadastrar`
        - Visualizar: `/cms/posts?id=1`
        - Editar: `/cms/posts/editar?id=1`

### 7. Gestão Páginas
- **Página Inicial**

</details>

# 📋 Briefing:

<details>
<summary><b>🗂️ Página Inicial</b></summary>

- **Banner Slideshow (Hero)**
    - Imagem Background
    - Link Produtos
- **Ofertas (Campanha)**
    - Nome Campanha
    - Descrição Oferta
    - Imagem Background
    - Link Produtos
- **Produtos Gênero Feminino (Carrosel)**
    - Últimos Adicionados (Lim. 12)
    - Nome Produto
    - Preço
- **Banner Promoções Main (CTA)**
    - Nome Banner
    - Descrição Banner
    - Imagem Background
    - Link Promoções
- **Mais Vendidos (Carrosel)**
    - Mais Vendidos (Unissex - Lim. 12)
    - Nome Produto
    - Preço
**Produtos Gênero Masculino (Carrosel)**
    - Últimos Adicionados (Lim. 12)
    - Nome Produto
    - Preço
- **Top Marcas (Carrosel)**
    - Logo Marcas
- **Newsletter (Lead)**
    - Imagem Background
    - Oferta (Isca)
    - Regras Oferta
    - Form E-mail
    - Box Icons
        - Nome
        - Descrição

</details>

<details>
<summary><b>🎁 Página Produtos (Gênero/Categoria)</b></summary>

- **Slideshow de Campanha (Hero)**
    - Imagem Background
    - Link Produtos
- **Produtos Miniatura (Categorização)**
    - Nome
    - Imagem
- **Container Ads (Campanha)**
    - Imagem
    - Link Produtos
- **Mais Baratos (Carrosel)**
    - Mais Vendidos
    - Nome Produto
    - Preço
- **Container Ads (Campanha)**
    - Imagem
    - Link Produtos
- **Novos (Carrosel)**
    - Mais Vendidos
    - Nome Produto
    - Preço
- **Banner (CTA)**
    - Nome Banner
    - Descrição Banner
    - Imagem Background
    - Link Produtos

</details>

<details>
<summary><b>🎯 Página Filtro</b></summary>

- **Banner (Hero)**
    - Imagem Background
    - Breadcrumb
- **Sidebar (Filtro)**
    - Gênero
    - Tipo de Produto
    - Tamanho
    - Marca
    - Cor
    - Preço
- **List (Card Grid)**
    - Ordenação
        Mais Populares
        Mais Vendidos
        Lançamentos
        Ofertas
        Maior Preço
        Menor Preço
        Melhor Avaliados
    - Card Container
    - Navigator
- **Ofertas (Carrosel)**
- **Novidade (Carrosel)**

</details>

<details>
<summary><b>🧺 Produto Individual</b></summary>

- **Breadcrumb**
- **Informação Produto**
    - Miniaturas
    - Imagem Destaque
    - Descrições
        - Marca
        - Nome
        - Valor Unitário (até 12x)
        - Cor
        - Tamanho
    - Botão Comprar
    - Medidas
    - Frete
    - Descrição
    - Detalhes
- **Comentários (Carrosel)**
    - Nome Cliente
    - Classificação
    - Descrição
    - Data
- **Produtos Semelhantes (Carrosel)**
- **Ofertas Gênero (Carrosel)**

</details>

<details>
<summary><b>🔑 Página Autenticação (Cliente)</b></summary>

- **Login**
    - Logo
    - Formulário

- **Cadastro**
    - Logo
    - Termos de privacidade
    - Formulário (PF/PJ)
    - Mensagem Sucesso

- **Redefinir Senha**
    - Logo
    - Formulário
    - Mensagem Sucesso

</details>

<details>
<summary><b>🎛️ Conta (Cliente)</b></summary>

- **Breadcrumb**
- **Informações Cliente**
    - Dados Pessoais
    - Meus pedidos
    - Endereços
    - Atendimento

- **Favoritos (Header)**
    - Card Container

</details>

<details>
<summary><b>🛒 Carrinho (Cliente)</b></summary>

- **Headline (Quantidade Itens)**
- **List Container**
    - Imagem Produto
    - Descrição (Marca/Nome)
    - Quantidade (Alterar)
    - Valor Unitário
    - Subtotal
    - Botão Remover
- **Campo de CEP (Cálculo de Frete)**
- **Resumo Compra**
    - Subtotal
    - Frete
    - Total (até 12x)
- **Produtos Relacionados (Carrosel**
    - Mais Vendidos (Gênero, Categoria e Tipo - Lim. 12)
    - Nome Produto
    - Preço

</details>

<details>
<summary><b>💳 Pagamento (Cliente)</b></summary>

- **Página Finalizar Login**
    - Header Steps
    - Formulário

- **Página Finalizar Compra**
    - Header Steps
    - Seleção Endereço
    - Forma Pagamento
    - Container Produtos Pedido
        - Imagem
        - Marca
        - Nome
        - Quantidade
        - Valor Unitário
        - Subtotal
        - Taxa de Entrega
        - Total (até 12x)
    - Cupom Desconto

- **Página Confirmação**
    - Resumo Pedido

</details>

# 🙆‍♂️ Imagens Créditos:

- **Fontes** 🥰
    - Netshoes: https://www.netshoes.com.br/
    - Dafiti: https://www.dafiti.com.br/
    - Zattini: https://www.zattini.com.br/

## 💼 Tecnologias utilizadas:

- #### Angular (Front)
  - HTML
  - SCSS
  - JavaScript
  - TypesScript

- #### Java (Back)
  - Spring Boot

- #### SQL (Banco)
  - PostgreSQL

<h2>🦄 Autor</h2>

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/bhigoreduardo">
        <img src="https://avatars.githubusercontent.com/u/96431991?v=4" width="100px;" alt="Foto do Higor Eduardo no GitHub"/><br>
        <sub>
          <b>Higor Eduardo</b>
        </sub>
      </a>
    </td>
  </tr>
</table>