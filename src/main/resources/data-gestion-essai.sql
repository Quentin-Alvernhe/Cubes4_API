INSERT INTO family (name, deleted) values
('Rosé', 0),
('Blanc', 0),
('Rouge', 0),
('Pétillant', 0),
('Champagne', 0),
('Spiritueux', 0),
('Whisky', 0),
('Rhum', 0),
('Cognac', 0);

INSERT INTO supplier (name, adress, email, phone_number, deleted) values
('AG Vins', "Bordeaux, France", "contact@agvins.fr", "01 23 45 67 89", 0),
('Les Grappes PRO', "Paris, France", "info@lesgrappespro.fr", "02 34 56 78 90", 0),
('NRJ Wines', "Paris, Île de France, France", "serviceclient@nrjwines.com", "03 45 67 89 01", 0),
('Velayos', "1 Avenue Jean Alfonséa, Parc  Ecchobloc Bâtiment 2, 33270 Floirac, France", "contact@velayos.fr", "05 56 06 46 12", 0),
('Laplace CDP', "Non spécifiée", "contact@laplacecdp.fr", "04 56 78 90 12", 0),
('Le Goût du Vin', "Non spécifiée", "legoutduvin@contact.fr", "06 09 21 24 73", 0),
('Vinodiff', '7, rue des Charpentiers, 13150 Tarascon, France', 'contact@vinodiff.com', '04 90 49 40 66', 0),
('Carniatio', '6, route de Stains, 94380 Bonneuil-sur-Marne, France', 'contact@carniato.com', '01 43 77 39 49', 0);

INSERT INTO product (name, quantity, reliquat, price, purchase_price, supplier_id, image, description, degustation, deleted) values
("Boukman Rhum", 25, 25, 4990, 3500, 8, '/images/image1.jpg', 'Un rhum haïtien distinctif, Boukman tire son caractère de la canne à sucre et des épices locales, offrant des notes de clou de girofle et de cannelle.', 'Idéal pour un ti-punch, servez-le avec un zeste de citron vert et un peu de sucre de canne.', 0),
("Aberlour A'bunadh Whisky", 30, 30, 7990, 5500, 4, '/images/image2.jpg', 'Un Scotch riche et corsé, vieilli en fûts de sherry, offrant des arômes complexes de fruits secs, d\'épices et de chocolat.', 'À déguster pur pour apprécier pleinement sa profondeur de saveurs.', 0),
("Havana  CLub Union Rhum", 15, 15, 38990, 28000, 8, '/images/image3.jpg', 'Un rhum cubain élégant, Union combine des notes de tabac et de fruits tropicaux, avec une finition suave et équilibrée.', 'Parfait en digestif, ou accompagné d\'un cigare cubain de qualité.', 0),
("Ballantine\'s Whiskey 4.5L", 20, 20, 13300, 9500, 5, '/images/image2.jpg', 'Un blend écossais classique, avec des notes de miel, de vanille et de chêne, Ballantine\'s offre une douceur équilibrée.', 'Idéal en cocktail ou simplement avec un peu d\'eau pour en révéler les arômes.', 0),
("Ricard Gallon Balancelle 4.5L", 10, 10, 15490, 11000, 2, '/images/image2.jpg', 'Un pastis traditionnel français, Ricard est un mélange rafraîchissant d\'anis et d\'herbes provençales.', 'À savourer allongé d\'eau fraîche pour une expérience rafraîchissante.', 0),
("The Glenlivet 21ans Whiskey", 40, 40, 27500, 19500, 4, '/images/image2.jpg', 'Vieilli dans une variété de fûts, ce whisky offre une complexité remarquable avec des notes de fruits mûrs et d\'épices.', 'Se déguste pur, lentement, pour apprécier sa richesse et sa finesse.', 0),
("The Glenlivet 25ans Whiskey", 35, 35, 42500, 30000, 4, '/images/image2.jpg', 'Un whisky d\'exception, marqué par un vieillissement prolongé qui lui confère des notes boisées et un caractère profond.', 'À savourer lentement pour découvrir son bouquet complexe et sa longue finale.', 0),
("Coffret Jameson avec 2 Verres", 50, 50, 4590, 3300, 7, '/images/image2.jpg', 'Un ensemble cadeau classique, le Jameson est un whiskey irlandais doux et floral, idéal pour les amateurs de whisky.', 'Parfait pour un whisky sour ou simplement avec un peu d\'eau.', 0),
("G.H. Mumm Grand Cordon Champagne", 50, 50, 3914, 2800, 1, '/images/image2.jpg', 'Un champagne vibrant et élégant, avec des bulles fines et des notes de fruits frais et de brioche.', 'Excellent en apéritif ou pour célébrer un moment spécial.', 0),
("Chivas Regal 12ans 1.5L", 100, 100, 8990, 6500, 2, '/images/image2.jpg', 'Un Scotch blend riche et onctueux, Chivas 12 ans offre un équilibre parfait entre douceur et notes de fruits et d\'épices.', 'Délicieux pur ou sur glace, parfait pour un moment de détente.', 0),
("Avion Silver Tequila", 20, 20, 5290, 3800, 3, '/images/image2.jpg', 'Une tequila pure et cristalline, Avion Silver offre des notes d\'agave fraîche et une douceur subtile.', 'Idéale pour des margaritas ou pour être dégustée pure.', 0),
("Avion Añejo Tequila", 25, 25, 6990, 5000, 3, '/images/image2.jpg', 'Vieillie en fûts de chêne, cette tequila présente des notes complexes de vanille et de caramel avec une finition lisse.', 'Parfaite pour siroter lentement, éventuellement avec un peu de citron vert.',0),
("Château Margaux Grand Vin", 10, 10, 50000, 35000, 6, '/images/image2.jpg', 'Un vin rouge de Bordeaux de renommée mondiale, élégant et puissant, avec des arômes de fruits noirs et un soupçon de terre.', 'Idéal avec des plats de viande rouge ou du fromage affiné.',0),
("Grés d'or Gallician IGP Rouge", 40, 40, 340, 210, 1, '/images/image2.jpg', 'Un vin rouge du sud de la France, léger et fruité, avec des notes de baies rouges et une touche d\'épices.', 'Parfait pour accompagner des plats méditerranéens ou à boire seul.',0),
("Chateau Sipian Medoc Collection Rouge 2019", 25, 25, 1190, 890, 6, '/images/image2.jpg', 'Un vin de Bordeaux riche et structuré, offrant des notes de cassis et de bois de cèdre, avec des tanins soyeux.', 'Idéal avec des viandes rôties ou des fromages corsés.',0),
("Cheval Noir Saint-Emilion 2021", 20, 20, 1290, 900, 4, '/images/image2.jpg', 'Un vin élégant et complexe de Saint-Émilion, avec des arômes de fruits noirs mûrs et une touche de chêne.', 'Parfait avec des plats de viande rouge ou un plateau de fromages.',0),
("Chateau Haut Piquat AOC Rouge 2019", 15, 15, 999, 600, 7, '/images/image2.jpg', 'Un Bordeaux classique avec une belle structure, mêlant fruits rouges et notes épicées, avec une finition élégante.', 'Excellent avec des viandes grillées ou des plats en sauce.',0),
("Penfolds Grange 2015", 25, 25, 75000, 60000, 2, '/images/image2.jpg', 'Un vin australien iconique, riche et puissant, avec des couches complexes de fruits, d\'épices et de chêne.', 'À déguster avec des plats robustes ou à conserver pour une occasion spéciale.',0),
("Domaine de la Romanée-Conti La Tâche 2016", 15, 15, 250000, 200000, 6, '/images/image2.jpg', 'Un Pinot Noir d\'exception, célèbre pour sa finesse et sa complexité, avec des notes de cerise et de terre humide.', 'Idéal avec des plats de gibier ou des fromages affinés.',0),
("Château Margaux 2015", 40, 40, 65000, 50000, 1, '/images/image2.jpg', 'Un grand cru classé, symbole d\'élégance et de raffinement, offrant un bouquet de fruits noirs et de fleurs.', 'Se marie parfaitement avec des mets raffinés ou pour une dégustation méditative.',0),
("Solaia Antinori 2017", 35, 35, 23000, 18000, 3, '/images/image2.jpg', 'Un vin italien de Toscane, riche et complexe, avec des notes de fruits noirs, d\'épices et un soupçon de vanille.', 'Idéal avec des plats italiens classiques ou des viandes grillées.',0),
("Opus One 2016", 50, 50, 38000, 30000, 5, '/images/image2.jpg', 'Un vin de Napa Valley élégant et harmonieux, mélangeant des arômes de fruits noirs, de tabac et de chocolat.', 'Parfait avec un steak ou pour une occasion spéciale.',0),
("Montrachet Grand Cru 2017 (Domaine Leflaive)", 20, 20, 55000, 45000, 6, '/images/image2.jpg', 'Un Chardonnay exceptionnel, riche et complexe, avec des notes de fruits à noyau, de noisette et une minéralité distincte.', 'À apprécier avec des fruits de mer haut de gamme ou des plats à base de champignons.',0),
("Louis Roederer Cristal 2012", 30, 30, 25000, 18000, 8, '/images/image2.jpg', 'Un champagne de luxe, avec une finesse et une élégance remarquables, offrant des arômes de fruits blancs et d\'amandes.', 'Idéal pour célébrer des moments inoubliables ou en apéritif raffiné.',0),
("Château Haut-Brion 2014", 45, 45, 42000, 35000, 7, '/images/image2.jpg', 'Un vin de Bordeaux prestigieux, avec une richesse aromatique, mélangeant fruits noirs, tabac et une touche boisée.', 'Excellent avec des viandes rouges ou pour une dégustation spéciale.',0),
("Sassicaia 2015", 60, 60, 28000, 21000, 5, '/images/image2.jpg', 'Un Super Toscan renommé, offrant un équilibre parfait entre puissance et élégance, avec des notes de fruits rouges et d\'épices.', 'Idéal avec des plats italiens ou des fromages forts.',0),
("Krug Clos du Mesnil 2004", 10, 10, 110000, 90000, 3, '/images/image2.jpg', 'Un champagne d\'exception, issu d\'un seul vignoble, offrant une complexité rare avec des notes de fruits blancs et de brioche.', 'Parfait pour des occasions spéciales ou en apéritif de luxe.',0),
("Domaine De La Cornuliere Muscadet Sevre et Maine 2022", 40, 40, 520, 330, 4, '/images/image2.jpg', 'Un vin blanc frais et vif, avec des notes d\'agrumes et une belle minéralité, typique de la région de la Loire.', 'Excellent avec des fruits de mer ou des huîtres.',0),
("Rosé de la Vallée Étoilée", 50, 50, 1899, 850, 6, '/images/image2.jpg', 'Un rosé délicat et fruité, avec des notes de fraises et de fleurs, parfait pour une journée ensoleillée.', 'À savourer en apéritif ou avec des plats légers comme des salades ou des grillades.',0);

INSERT INTO family_product (product_id, family_id) values
(1,8),
(3,8),
(2,7),
(4,7),
(6,7),
(7,7),
(8,7),
(10,7),
(5,6),
(9,5),
(11,6),
(12,6),
(13,3),
(14,3),
(15,3),
(16,3),
(17,3),
(18,3),
(19,3),
(20,3),
(21,3),
(22,3),
(23,2),
(24,5),
(25,3),
(26,3),
(27,5),
(28,2),
(29,1);

INSERT INTO role (name) values ('admin'),('salarie'),('client'),('gestionnaire');

INSERT INTO user (id, lastname, firstname, login, password, role_id, deleted) values
(1,'DUPONT','Jean','jean.dupont@gmail.com', '$2a$10$XDSyiRYscMt3yL1FN8h64uzqQEQlvNtAISd7c/MFfKTfGA8vHkCK2', 1, 0),
(2,'DURAND','Paul','paul.durant@gmail.com', '$2a$10$XDSyiRYscMt3yL1FN8h64uzqQEQlvNtAISd7c/MFfKTfGA8vHkCK2', 2, 1),
(3,'MAFARO','Theo','theomafaro@gmail.com', '$2a$10$XDSyiRYscMt3yL1FN8h64uzqQEQlvNtAISd7c/MFfKTfGA8vHkCK2', 1, 0),
(4,'John','Wick','babayaga@continental.com', '$2a$10$XDSyiRYscMt3yL1FN8h64uzqQEQlvNtAISd7c/MFfKTfGA8vHkCK2', 3, 0);

INSERT INTO supplier_order (date,status, supplier_id) VALUES
('2023-11-15 09:43:56', 1, 2),
('2024-01-03 14:18:05', 3, 1),
('2023-11-29 15:15:04', 2, 3),
('2024-01-04 02:41:35', 1, 4),
('2024-01-12 23:33:48', 2, 6);

INSERT INTO supplier_order_line (quantity, price, order_id, product_id) VALUES
(10, 55, 2, 2),
(20, 280, 1, 3),
(15, 9, 4, 16),
(25, 2000, 5, 20),
(10, 50, 3, 12);

INSERT INTO customer_order (date,status, customer_id) VALUES
('2023-11-15 09:43:56', 4, 2),
('2024-01-03 14:18:05', 4, 1),
('2023-11-29 15:15:04', 4, 3),
('2024-01-04 02:41:35', 4, 4),
('2024-01-12 23:33:48', 4, 2);

INSERT INTO customer_order_line (quantity, price, customerorder_id, product_id) VALUES
(40, 55, 2, 2),
(50, 280, 1, 3),
(55, 9, 4, 16),
(55, 2000, 5, 20),
(50, 50, 3, 12);

INSERT INTO stock_history (quantity, date, product_id) VALUES
(25,"2023-11-15 14:29:44",1),
(80,"2024-01-03 14:29:44",1),
(50,"2024-01-04 03:36:01",1),
(30,"2024-01-04 03:36:01",1),
(45,"2024-01-04 03:36:01",1),
(30,"2024-01-04 03:36:01",1),
(30,"2024-01-16 18:43:21",2),
(15,"2024-01-13 16:45:39",3),
(20,"2024-01-13 16:45:52",4),
(10,"2024-01-13 16:46:07",5),
(40,"2024-01-13 16:46:24",6),
(35,"2024-01-13 16:46:39",7),
(50,"2024-01-13 16:46:58",8),
(50,"2024-01-13 16:47:11",9),
(100,"2024-01-13 16:47:30",10),
(20,"2024-01-13 16:47:48",11),
(25,"2024-01-13 16:48:05",12),
(10,"2024-01-13 16:48:27",13),
(40,"2024-01-13 16:48:51",14),
(25,"2024-01-13 16:49:04",15),
(20,"2024-01-13 16:49:22",16),
(15,"2024-01-13 16:49:34",17),
(25,"2024-01-13 16:49:47",18),
(15,"2024-01-13 16:50:03",19),
(40,"2024-01-13 16:50:18",20),
(35,"2024-01-13 16:50:43",21),
(50,"2024-01-13 16:50:56",22),
(20,"2024-01-13 16:51:09",23),
(30,"2024-01-13 16:51:21",24),
(45,"2024-01-13 16:51:42",25),
(60,"2024-01-13 16:52:00",26),
(10,"2024-01-13 16:52:23",27),
(40,"2024-01-13 16:52:50",28);