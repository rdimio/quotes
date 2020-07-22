DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id                    bigserial,
  password              VARCHAR(80),
  email                 VARCHAR(30) UNIQUE,
  login                 VARCHAR(20),
  PRIMARY KEY (id)
);

INSERT INTO users (password, login, email)
VALUES
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','admin','admin@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user1','user1@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user2','user2@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user3','user3@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user4','user4@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user5','user5@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user6','user6@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user7','user7@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user8','user8@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user9','user9@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user10','user10@gmail.com'),
('$2y$12$G3.H5B2vrDOfmrwl/he/8.IZ12zNXHqmRbm2FVoMiDdqkOHZCf8vW','user11','user11@gmail.com');

drop table if exists quotes cascade;
create table quotes
(id serial, title text, user_id int, primary key(id),
foreign key (user_id) references  users (id));
insert into quotes(title, user_id) values
('Lorem, ipsum dolor sit amet consectetur adipisicing elit. Atque, voluptates dolorum. Recusandae sed eum accusantium
soluta sit consectetur doloremque omnis saepe corrupti laboriosam beatae vel, reiciendis, cum exercitationem facere
nihil, deleniti aspernatur dolorum. Ducimus molestias, suscipit eos at amet omnis molestiae totam, rem incidunt quo
dolore voluptatum accusamus voluptatibus necessitatibus eaque numquam id? Enim ab nihil, nisi ratione aspernatur quidem
molestias amet deserunt quasi reprehenderit numquam repellat quam! Enim delectus exercitationem omnis aliquam tempore!
Veniam, voluptas provident? Dignissimos quis incidunt libero, debitis corrupti molestias odit modi repudiandae at
soluta. Nostrum odit quos repellat adipisci dicta molestias dolore aspernatur illo rerum!',1),
('Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolor reprehenderit voluptas accusantium, debitis numquam
asperiores.', 2),
('Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestiae voluptate tenetur atque amet? Consectetur, sunt.',3),
('Lorem ipsum dolor sit amet.',4),
('Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa.',5),
('Lorem ipsum dolor sit amet consectetur adipisicing elit. Eum nostrum id quidem labore, deserunt assumenda esse sed
voluptates.',6),
('agsdgsadksahgjkdsgkajsdasdsa', 7),
('sadjljads',8),
('lasdladsj',9),
('asdjkkasdjaskadkasdjkdas',10),
('kadskdsakasksaksaddksakakdkadadskdasas',11);

drop table if exists score;
create table score (
    user_id               INT NOT NULL,
    quote_id               INT NOT NULL,
    PRIMARY KEY (user_id, quote_id),
    FOREIGN KEY (user_id)
    REFERENCES users (id),
    FOREIGN KEY (quote_id)
    REFERENCES quotes (id)
);

insert into score (user_id, quote_id) values
(1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),
(1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),
(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),
(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),
(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),
(1,6),(2,6),(3,6),(4,6),(5,6),(6,6),
(1,7),(2,7),(3,7),(4,7),(5,7),
(1,8),(2,8),(3,8),(4,8),
(1,9),(2,9),(3,9),
(1,10),(2,10),
(1,11);
