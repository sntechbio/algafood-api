insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1)
insert into restaurante (nome, taxa_frete, cozinha_id) value ('Thai Delivery', 9.50, 1)
insert into restaurante (nome, taxa_frete, cozinha_id) value ('Tuk Tuk Comida Indiana', 15, 2)

insert into estado (id, nome) values (1, "Acre")
insert into estado (id, nome) values (2, "Alagoas")
insert into estado (id, nome) values (3, "São Paulo")

insert into cidade (id, nome, estado_id) values (1, "São Paulo", 3)
insert into cidade (id, nome, estado_id) values (2, "Piracicaba", 3)
insert into cidade (id, nome, estado_id) values (3, "Ribeirão Preto", 3)

insert into forma_pagamento (id, descricao) values (1, "Cartão de crédito");
insert into forma_pagamento (id, descricao) values (2, "Cartão de débito");
insert into forma_pagamento (id, descricao) values (3, "Dinheiro");

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);