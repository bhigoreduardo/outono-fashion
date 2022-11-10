ELEMENTOS GLOBAIS:
- Breadcrumb: produto.component.html
- Breadcrumb: 

TODO:
    - Slider price
    - Order By: Ideia de ngChange no click order
    - Header: Obter informações => Seed Banco
    - Navigate arrow change index and array com dots
    - Espiar produto
    - Nenhum Comentário template

<!-- BANNER -->
<div class="banner container">
    <div class="content">
        <div class="breadcrumb">
            <a>Início</a>
            <i class="fa fa-chevron-right"></i>
            <!-- Gênero -->
            <a>Feminino</a>
            <i class="fa fa-chevron-right"></i>
            <!-- Categoria -->
            <a>Roupas</a>
            <i class="fa fa-chevron-right"></i>
            <!-- Tipo -->
            <a>Camisas</a>
            <i class="fa fa-chevron-right"></i>
            <!-- Marca -->
            <a>Calvin Klein</a>
        </div>
        <!-- Categoria + Gênero -->
        <h2>Camisas Feminino</h2>
    </div>
</div>
<!-- / BANNER -->

.banner .content {
    padding: 30px;
    background-color: $color-gray-200;
    margin-bottom: 100px;

    h2 {
        font-size: $font-size-h2;
        font-weight: $font-weight-regular;
        line-height: $line-height-h2;
    }
}