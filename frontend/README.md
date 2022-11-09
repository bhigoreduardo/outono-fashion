ELEMENTOS GLOBAIS:
- Breadcrumb: produto.component.html
- Breadcrumb: 

TODO:
    - Slider price
    - Order By: Ideia de ngChange no click order
    - Header: Obter informações

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

    .breadcrumb {
        margin-bottom: 20px;

        a {
            font-size: $font-size-sm;
            line-height: $line-height-sm;
            color: $color-gray-700;
            transition: all 200ms ease-in;

            @media screen and (max-width: 580px) {
                font-size: $font-size-xs;
                line-height: $line-height-xs;
            }

            &:hover {
                color: $color-warning;
            }
        }

        i {
            font-size: 10px;
            color: $color-warning;
            margin: 18px;
        }
    }

    h2 {
        font-size: $font-size-h2;
        font-weight: $font-weight-regular;
        line-height: $line-height-h2;
    }
}