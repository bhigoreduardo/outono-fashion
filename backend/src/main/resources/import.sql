
-- Permissao Gerente
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_USUARIOS', 'Permite criar ou editar usuário existente.'), ('EDITAR_PERMISSAO', 'Permite criar ou editar permissão existente.'), ('EDITAR_GRUPO', 'Permite criar ou editar grupo existente.'), ('CONSULTAR_USUARIOS', 'Permite consultar usuário existente.');

-- Permissao Gerente/Cadastrador
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_FORMA_PAGAMENTO', 'Permite criar ou editar forma de pagamento.'), ('EDITAR_PRODUTO', 'Permite criar ou editar produto cadastrado.');

-- Permissao Gerente/Vendedor
INSERT INTO permissao (nome, descricao) VALUES ('EDITAR_PEDIDO', 'Permite editar informações do pedido.'), ('GERAR_RELATORIOS', 'Permite gerar relatórios.');

-- Permissao Gerente/Cadastrador/Vendedor/Cliente
INSERT INTO permissao (nome, descricao) VALUES ('CONSULTAR PRODUTO', 'Permite visualizar informações do produto.'), ('EDITAR_DADOS', 'Permite criar ou editar dados individuais.'), ('CRIAR_PEDIDO', 'Permite criar novo pedido');

-- Grupo
INSERT INTO grupo (nome) VALUES ('Gerente'), ('Vendedor'), ('Cadastrador'), ('Cliente');

-- Pagamento
INSERT INTO pagamento (descricao, ativo, data_atualizacao) VALUES ('Cartão de crédito', true, now()), ('Cartão de débito', true, now()), ('Dinheiro', true, now()), ('PIX', true, now());

-- Gênero
INSERT INTO genero (descricao) VALUES ('Masculino'), ('Feminino'), ('Menino'), ('Menina'), ('Unissex');

-- Categoria
INSERT INTO categoria (descricao) VALUES ('Calçados'), ('Roupas'), ('Acessórios');

-- Tipo
-- INSERT INTO tipo (descricao, categoria_id) VALUES	('Botas', 1), ('Chinelos', 1), ('Chuteiras', 1), ('Crocs', 1), ('Sapatênis', 1), ('Tênis', 1), ('Bermudas', 2), ('Calças', 2), ('Camisas', 2), ('Jaquetas', 2), ('Moletons', 2), ('Shorts', 2), ('Vestidos', 2), ('Bonés', 3), ('Malas', 3), ('Meias', 3), ('Mochilas', 3), ('Óculos', 3), ('Relógios', 3);
INSERT INTO tipo (descricao) VALUES	('Botas'), ('Chinelos'), ('Chuteiras'), ('Crocs'), ('Sapatênis'), ('Tênis'), ('Bermudas'), ('Calças'), ('Camisas'), ('Jaquetas'), ('Moletons'), ('Shorts'), ('Vestidos'), ('Bonés'), ('Malas'), ('Meias'), ('Mochilas'), ('Óculos'), ('Relógios');

-- Tamanho
INSERT INTO tamanho	(descricao)	VALUES ('PP'), ('P'), ('M'), ('G'), ('GG'), ('XG'), ('20'), ('22'), ('24'), ('26'), ('28'), ('30'), ('32'), ('34'), ('36'), ('38'), ('40'), ('42'), ('44'), ('46'), ('48'), ('50'), ('Único');

-- Marca
INSERT INTO marca (descricao) VALUES ('Adidas'), ('Amora'), ('Asics'), ('Aramis'), ('Armadilho'), ('Calvin Klein'), ('Colcci'), ('Capricho'), ('Caterpillar'), ('Coca Cola'), ('Everlast'), ('Fila'), ('Kappa'), ('Mizuno'), ('Nike'), ('Oakley'), ('Olympikus'), ('Puma'), ('Lacoste'), ('Reserva');

-- Cor
INSERT INTO cor (descricao, valor) VALUES ('Amarelo', '#F29D36'), ('Azul Claro', '#7DA4CE'), ('Azul Escuro', '#0E4B8B'), ('Bege', '#E5D0BD'), ('Branco', '#FFFFFF'), ('Cinza', '#C3C3C3'), ('Dourado', '#CEA227'), ('Laranja', '#FE6B23'), ('Marrom', '#966D4F'), ('Preto', '#000000'), ('Rosa', '#FF0D97'), ('Roxo', '#752F92'), ('Verde', '#019934'), ('Verde Escuro', '#14381E'), ('Verde Claro', '#77AE6D'), ('Vermelho', '#A40313');

-- Produto

-- Masculino -> Calçados -> Botas
INSERT INTO produto	(nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Bota Chelsea Colcci Israel Preta', 'Bota Coturno Masculino Colcci Israel Preta', 'Bota Chelsea Colcci Israel Preta<br>Bota Coturno Masculino Colcci Israel Preta<br>Bico: Redondo<br>Medidas da peça: 12x20cm (Altura X Circunferência)<br>Características: Possui puxador posterior para facilitar o calce<br><br>VERSÁTIL E MODERNA: Coringas para os dias mais frios ou chuvosos, as botas masculinas podem compor looks cheios de estilo que vão do urbano garage ao elegante, podendo ser usadas até mesmo para um visual mais formal para o trabalho!<br><br>RESISTENTE E ATEMPORAL: O Couro é o material obtido através da pele de animal curtida, e pode ter diversos acabamentos. Apresenta resistência e durabilidade, o que torna um material nobre muito utilizado na confecção de calçados, roupas e acessórios. A escolha perfeita para quem deseja um produto duradouro!<br><br>Sobre a marca: Há 30 anos a Colcci é considerada uma das marcas mais influentes da moda brasileira, graças aos seus produtos que aliam qualidade, sofisticação e informação de moda. A marca é um Must-Have de qualquer guarda-roupa, e conta com peças para todas as ocasiões.<br>Produto enviado com nota fiscal!', '18.00', '9.50', '28.00', '500.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 1, 1, 1, 7);

INSERT INTO produto	(nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Bota Coturno Colcci José Café', 'Bota Coturno Masculino Colcci José Café', 'Bota Coturno Colcci José Café<br>Bota Coturno Masculino Colcci José Café<br>Bico: Redondo<br>Medidas da peça: 12x30cm (Altura X Circunferência)<br>Características: Possui solado tratorado<br><br>VERSÁTIL E MODERNA: Coringas para os dias mais frios ou chuvosos, as botas masculinas podem compor looks cheios de estilo que vão do urbano garage ao elegante, podendo ser usadas até mesmo para um visual mais formal para o trabalho!<br><br>RESISTENTE E ATEMPORAL: O Couro é o material obtido através da pele de animal curtida, e pode ter diversos acabamentos. Apresenta resistência e durabilidade, o que torna um material nobre muito utilizado na confecção de calçados, roupas e acessórios. A escolha perfeita para quem deseja um produto duradouro!<br><br>Sobre a marca: Há 30 anos a Colcci é considerada uma das marcas mais influentes da moda brasileira, graças aos seus produtos que aliam qualidade, sofisticação e informação de moda. A marca é um Must-Have de qualquer guarda-roupa, e conta com peças para todas as ocasiões.<br>Produto enviado com nota fiscal!', '18.00', '9.50', '28.00', '500.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 1, 1, 1, 7);
	
INSERT INTO produto	(nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Bota Chelsea Colcci Israel Café', 'Bota Coturno Masculino Colcci Israel Café', 'Bota Chelsea Colcci Israel Café<br>Bota Coturno Masculino Colcci Israel Café<br>Bico: Redondo<br>Medidas da peça: 12x20cm (Altura X Circunferência)<br>Características: Possui puxador posterior para facilitar o calce<br><br>VERSÁTIL E MODERNA: Coringas para os dias mais frios ou chuvosos, as botas masculinas podem compor looks cheios de estilo que vão do urbano garage ao elegante, podendo ser usadas até mesmo para um visual mais formal para o trabalho!<br><br>RESISTENTE E ATEMPORAL: O Couro é o material obtido através da pele de animal curtida, e pode ter diversos acabamentos. Apresenta resistência e durabilidade, o que torna um material nobre muito utilizado na confecção de calçados, roupas e acessórios. A escolha perfeita para quem deseja um produto duradouro!<br><br>Sobre a marca: Há 30 anos a Colcci é considerada uma das marcas mais influentes da moda brasileira, graças aos seus produtos que aliam qualidade, sofisticação e informação de moda. A marca é um Must-Have de qualquer guarda-roupa, e conta com peças para todas as ocasiões.<br>Produto enviado com nota fiscal!', '18.00', '9.50', '28.00', '500.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 1, 1, 1, 7);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('bota-chelsea-colcci-israel-preta-1-1', 'webp', 10, 1), ('bota-chelsea-colcci-israel-preta-1-2', 'webp', 10, 1), ('bota-chelsea-colcci-israel-preta-1-3', 'webp', 10, 1), ('bota-chelsea-colcci-israel-preta-1-4', 'webp', 10, 1), ('bota-chelsea-colcci-israel-preta-1-5', 'webp', 10, 1), ('bota-chelsea-colcci-israel-preta-1-6', 'webp', 10, 1);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('bota-coturno-colcci-jose-cafe-2-1', 'webp', 10, 2), ('bota-coturno-colcci-jose-cafe-2-2', 'webp', 10, 2), ('bota-coturno-colcci-jose-cafe-2-3', 'webp', 10, 2), ('bota-coturno-colcci-jose-cafe-2-4', 'webp', 10, 2), ('bota-coturno-colcci-jose-cafe-2-5', 'webp', 10, 2), ('bota-coturno-colcci-jose-cafe-2-6', 'webp', 10, 2);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('bota-chelsea-colcci-israel-cafe-3-1', 'webp', 10, 3), ('bota-chelsea-colcci-israel-cafe-3-2', 'webp', 10, 3), ('bota-chelsea-colcci-israel-cafe-3-3', 'webp', 10, 3), ('bota-chelsea-colcci-israel-cafe-3-4', 'webp', 10, 3), ('bota-chelsea-colcci-israel-cafe-3-5', 'webp', 10, 3);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (19, 280.90, 1, 10, 17), (20, 280.90, 1, 10, 18), (23, 280.90, 1, 10, 19), (19, 270.90, 2, 10, 17), (20, 270.90, 2, 10, 18), (19, 270.90, 2, 10, 19), (19, 270.90, 2, 10, 20), (20, 200.90, 3, 10, 18), (23, 200.90, 3, 10, 19);

-- Feminino -> Roupas -> Camisas
INSERT INTO produto	(nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Camisa Calvin Klein Reta Listrada Rosa', 'Camisa Calvin Klein Reta Listrada Rosa Feminina', 'Camisa Calvin Klein Reta Listrada Rosa<br>Camisa GAP Reta Listrada Rosa Feminina<br>Camisa GAP Reta Listrada Rosa/Branca<br>Manga: Longa<br>Gola: Ponta<br>Fecho: Botão<br>Bolsos: 1<br>Medidas da peça no tamanho G: Ombro: 15cm/ Ombro a Ombro: 44cm/ Manga: 63cm/ Busto: 116cm /Comprimento: 64cm<br>Medidas do Modelo: Altura 1,81m / Busto: 102cm / Cintura: 84cm / Quadril: 113cm.<br>Produto enviado com nota fiscal!', '18.00', '5.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 2, 9, 6);

INSERT INTO produto	(nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Camisa Armadilho Manga Longa Algodão Branco', 'Camisa Armadilho Manga Longa Algodão Branco Feminina', 'Camisa Armadilho Manga Longa Algodão Branco<br>Camisa Armadilho Manga Longa Algodão Branco Feminina<br>Camisa manga longa básica algodão com poliamida e elastano, toque macio, ótimo caimento modelagem ajustada com pences. Fechamento botão.<br>Produto enviado com nota fiscal!', '18.00', '5.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 2, 9, 5);

INSERT INTO produto	(nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Camisa Facinelli by MOONCITY Amarração Rosa', 'Camisa Armadilho by MOONCITY Amarração Rosa Feminina', 'Camisa Armadilho by MOONCITY Amarração Rosa<br>Camisa Armadilho by MOONCITY Amarração Rosa Feminina<br>Modelagem: Reta<br>Gola: Ponta<br>Manga: Longa<br>Fecho: Botão<br>Detalhes: Possui amarração na barra. <br><br>Medidas da peça no tamanho P:<br>• Ombro: 14cm<br>• Ombro a ombro: 45cm<br>• Comprimento da manga: 65cm<br>• Largura da manga: 48cm<br>• Comprimento da peça: 66cm<br>• Busto: 118cm<br>Medidas do Modelo: Altura 1,70m/ Busto: 86cm/ Cintura: 65cm/ Quadril: 87cm.<br><br>CLÁSSICA E ELEGANTE: As camisas são peças obrigatórias em qualquer guarda-roupa, pois são super fáceis de combinar e dão automaticamente aquele ar arrumado ao look. Mas se engana quem acredita que ela não pode compor looks mais descontraídos. Basta usá-la de formas diferentes, como deixar um lado solto e outro dentro da calça ou até mesmo adicionar acessórios mais pesados para um visual diferente!<br>Produto enviado com nota fiscal!', '18.00', '5.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 2, 9, 5);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('camisa-calvin-klein-reta-listrada-rosa-4-1', 'webp', 11, 4), ('camisa-calvin-klein-reta-listrada-rosa-4-2', 'webp', 11, 4), ('camisa-calvin-klein-reta-listrada-rosa-4-3', 'webp', 11, 4), ('camisa-calvin-klein-reta-listrada-rosa-4-4', 'webp', 11, 4);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('camisa-armadilho-manga-longa-algodao-branco-5-1', 'webp', 5, 5), ('camisa-armadilho-manga-longa-algodao-branco-5-2', 'webp', 5, 5), ('camisa-armadilho-manga-longa-algodao-branco-5-3', 'webp', 5, 5), ('camisa-armadilho-manga-longa-algodao-branco-5-4', 'webp', 5, 5);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('camisa-facinelli-by-mooncity-amarracao-rosa-6-1', 'webp', 11, 6), ('camisa-facinelli-by-mooncity-amarracao-rosa-6-2', 'webp', 11, 6), ('camisa-facinelli-by-mooncity-amarracao-rosa-6-3', 'webp', 11, 6), ('camisa-facinelli-by-mooncity-amarracao-rosa-6-4', 'webp', 11, 6);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (19, 280.90, 4, 11, 2), (20, 280.90, 4, 11, 3), (20, 280.90, 4, 11, 4), (20, 300.90, 5, 5, 2), (20, 300.90, 5, 5, 3), (20, 300.90, 5, 5, 4), (20, 300.90, 6, 11, 2), (20, 300.90, 6, 11, 3);

-- Feminino -> Roupas -> Shorts
INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Short Feminino Fitness Amarração Marisa', 'Short Feminino Fitness Amarração Marisa Preto', 'Short Feminino Fitness Amarração Marisa<br>Short Feminino Fitness Amarração Marisa Preto<br>Short Feminino Fitness Amarração Marisa Short feminino moda fitness, confeccionado em tecido poliéster.<br> Possui cós com elástico e tira para amarração de ajuste, pequenos furos no tecido que auxiliam na ventilação, forro, acabamento e costura no tom.<br>Roupa Feminina - Moda Fitness - Bermudas e Shorts MODA FITNES.<br> Medidas da ModeloAltura: 1,79 Busto: 83cm Cintura: 64cmQuadril: 92cm Modelo veste tamanho: P', '18.00', '5.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 2, 12, 1);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('short-feminino-fitness-amarracao-marisa-7-1', 'webp', 10, 7), ('short-feminino-fitness-amarracao-marisa-7-2', 'webp', 10, 7), ('short-feminino-fitness-amarracao-marisa-7-3', 'webp', 10, 7), ('short-feminino-fitness-amarracao-marisa-7-4', 'webp', 10, 7);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (19, 80.90, 7, 10, 3), (19, 80.90, 7, 10, 4), (19, 80.90, 7, 10, 5);

-- Feminino -> Calçados -> Botas
INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Bota Coturno Feminino Tratorada City DUBUY', 'Bota Coturno Feminino Tratorada City DUBUY Preto', 'Bota Coturno Feminino Tratorada City DUBUY<br>Bota Coturno Feminino Tratorada City DUBUY Preto<br>Bota Coturno Feminino Tratorada City DUBUY 1105LI Marrom/Capuccino ou Preto Fosco <br> <br>Marca: DUBUY <br>Numerações: 33 ao 42 <br>Zíper lateral <br>Forro antibacteriano <br>Altura do Salto: 4,2 cm <br>Altura do cano: 15,5 cm <br>Forma do calçado: Normal <br>Material Interno: Cacharrel espumado (confort) <br>Solado: Micro Expandido - (Antiderrapante – Tratorado) <br>Reforço de costura lateral do cabedal (dando mais durabilidade). <br>Diferencial: Bota estilo coturno com sola tratorada, que garante durabilidade, praticidade e conforto.<br>90 dias de Garantia contra defeito de Fabricação (Garantia apenas do calçado)<br>Produto enviado com nota fiscal!', '18.00', '5.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 1, 1, 2);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('bota-coturno-feminino-tratorada-city-dubuy-8-1', 'webp', 10, 8), ('bota-coturno-feminino-tratorada-city-dubuy-8-2', 'webp', 10, 8), ('bota-coturno-feminino-tratorada-city-dubuy-8-3', 'webp', 10, 8);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (20, 89.90, 8, 10, 16), (20, 89.90, 8, 10, 17), (20, 99.90, 8, 10, 18);

-- Feminino -> Calçados -> Chinelos
INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Chinelo Colcci Rio Branco', 'Chinelo Colcci Rio Branco Techfit', 'Chinelo Colcci Rio Branco<br>Chinelo Colcci Rio Branco Techfit<br>Tecnologia: Techfit - um composto tecnológico desenvolvido exclusivamente para a Colcci. Além de usarmos material 100% vegano, nossa matéria prima é 100% reciclável e ecologicamente correto, podendo ser reutilizado para a fabricação de novos produtos.<br><br>Há 30 anos a Colcci é considerada uma das marcas mais influentes da moda brasileira, graças aos seus produtos que aliam qualidade, sofisticação e informação de moda. A marca é um Must-Have de qualquer guarda-roupa, e conta com peças para todas as ocasiões.<br><br>Peças essenciais para arrematar um look, os calçados versáteis com suas combinações fáceis e estilosas, são os modelos que ficam bem com qualquer tipo de visual e, por isso, são indispensáveis<br>Produto enviado com nota fiscal!', '18.00', '5.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 1, 2, 7);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('chinelo-colcci-rio-branco-9-1', 'webp', 5, 9), ('chinelo-colcci-rio-branco-9-2', 'webp', 5, 9), ('chinelo-colcci-rio-branco-9-3', 'webp', 5, 9), ('chinelo-colcci-rio-branco-9-4', 'webp', 5, 9); 

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (33, 79.90, 9, 5, 16), (20, 79.90, 9, 5, 17), (20, 99.90, 9, 5, 18);

-- Feminino -> Calçados -> Crocs
INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Sandália Crocs Crocbrand', 'Sandália Crocs Crocbrand Azul Escuro', 'Chinelo Colcci Rio Branco<br>Sandália Crocs Crocbrand Azul Escuro<br>Bico: Redondo<br><br>Peças essenciais para arrematar um look, os calçados versáteis com suas combinações fáceis e estilosas, são os modelos que ficam bem com qualquer tipo de visual e, por isso, são indispensáveis.<br>Produto enviado com nota fiscal!', '18.00', '15.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 1, 4, 20);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('sandalia-crocs-crocbrand-10-1', 'webp', 3, 10), ('sandalia-crocs-crocbrand-10-2', 'webp', 3, 10), ('sandalia-crocs-crocbrand-10-3', 'webp', 3, 10), ('sandalia-crocs-crocbrand-10-4', 'webp', 3, 10), ('sandalia-crocs-crocbrand-10-5', 'webp', 3, 10);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (33, 79.90, 10, 3, 16), (43, 79.90, 10, 3, 17), (63, 79.90, 10, 3, 18), (10, 79.90, 10, 3, 19);

-- Feminino -> Calçados -> Tênis
INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Tênis adidas Originals Superstar', 'Tênis adidas Originals Superstar Branco', 'Tênis adidas Originals Superstar<br>Tênis adidas Originals Superstar Branco<br>Tênis adidas Originals Superstar Branco/Preto<br>Bico: Redondo<br>Fechamento: Cadarço<br>Ocasião/Estilo: Casual<br>Material Externo: Couro<br>Material Interno: Têxtil<br>Material da Sola: Sintético<br>Produto enviado com nota fiscal!', '18.00', '15.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 1, 6, 1);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('tenis-adidas-originals-superstar-11-1', 'webp', 5, 11), ('tenis-adidas-originals-superstar-11-2', 'webp', 5, 11), ('tenis-adidas-originals-superstar-11-3', 'webp', 5, 11), ('tenis-adidas-originals-superstar-11-4', 'webp', 5, 11), ('tenis-adidas-originals-superstar-11-5', 'webp', 5, 11), ('tenis-adidas-originals-superstar-11-6', 'webp', 5, 11);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (33, 79.90, 11, 5, 16), (33, 79.90, 11, 5, 17), (33, 80.90, 11, 5, 19);

-- Feminino -> Calçados -> Sapatênis
INSERT INTO produto (nome, descricao, detalhe, largura, altura, comprimento, peso, data_cadastro, data_atualizacao, ativo, genero_id, categoria_id, tipo_id, marca_id) VALUES ('Tênis Game Advance', 'Tênis Game Advance em couro Branco', 'Tênis Game Advance<br>Tênis Game Advance em couro Branco<br>Cabedal em couro e tecido. Solado em borracha. Forro em tecido. Crocodilo verde bordado na lateralForro em material reciclado, para um modelo sustentável<br>Produto enviado com nota fiscal!', '18.00', '15.00', '28.00', '200.00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 2, 1, 5, 19);

INSERT INTO imagem (nome, tipo, cor_id, produto_id) VALUES ('tenis-game-advance-12-1', 'webp', 5, 12), ('tenis-game-advance-12-2', 'webp', 5, 12), ('tenis-game-advance-12-3', 'webp', 5, 12), ('tenis-game-advance-12-4', 'webp', 5, 12), ('tenis-game-advance-12-5', 'webp', 5, 12);

INSERT INTO estoque (quantidade, preco, produto_id, cor_id, tamanho_id) VALUES (13, 79.90, 12, 5, 16), (23, 79.90, 12, 5, 17), (33, 80.90, 12, 5, 19);
