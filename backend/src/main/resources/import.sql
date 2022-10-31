-- Gênero
INSERT INTO genero
	(descricao)
	VALUES
	('Masculino'), ('Feminino'), ('Menino'), ('Menina'), ('Unissex');
	
-- Categoria
INSERT INTO categoria
	(descricao)
	VALUES
	('Calçados', 'Roupas', 'Acessórios');

-- Tipo
INSERT INTO tipo
	(descricao)
	VALUES
	('Botas', 'Chinelos', 'Chuteiras', 'Crocs', 'Sapatênis', 'Tênis',
	'Bermudas', 'Calças', 'Camisas', 'Jaquetas', 'Moletons', 'Shorts', 'Vestidos',
	'Bonés', 'Malas', 'Meias', 'Mochilas', 'Óculos', 'Relógios');
	
-- Tamanho
INSERT INTO tamanho
	(descricao)
	VALUES
	('PP', 'P', 'M', 'G', 'GG', 'XG', '20', '22', 24', '26', '28', '30', '32', '34',
	'36', '38', '40', '42', '44', '46', '48', '50', 'Único');
	
-- Marca
INSERT INTO marca
	(descricao)
	VALUES
	('Adidas', 'Amora', 'Asics', 'Aramis', 'Armadilho', 'Calvin Klein', 'Colcci',
	'Capricho', 'Caterpillar', 'Coca Cola', 'Everlast', 'Fila', 'Kappa', 'Mizuno',
	'Nike', 'Oakley', 'Olympikus', 'Puma', 'Lacoste', 'Reserva');
	
-- Cor
INSERT INTO cor
	(descricao)
	VALUES
	('Amarelo', 'Azul Claro', 'Azul Escuro', 'Bege', 'Bordô', 'Branco', 'Cinza',
	'Dourado', 'Laranja', 'Lilás', 'Marinho', 'Marrom', 'Preto', 'Rosa', 'Roxo',
	'Verde', 'Verde Escuro', 'Verde Claro', 'Vermelho', 'Vinho', 'Violeta');