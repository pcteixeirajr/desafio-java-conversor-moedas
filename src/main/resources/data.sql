-- Criação de Moedas
INSERT INTO moeda (nome_moeda) VALUES ('Ouro Real');
INSERT INTO moeda (nome_moeda) VALUES ('Tibar');

-- Criação de Taxa de Câmbio Inicial
INSERT INTO taxa_de_cambio (id_moeda_origem, id_moeda_destino, taxa_cambio, data_taxa_cambio)
VALUES
(1, 2, 2.5, NOW()),  -- 1 Ouro Real = 2.5 Tibares
(2, 1, 0.4, NOW());  -- 1 Tibar = 0.4 Ouro Real
