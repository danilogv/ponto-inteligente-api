CREATE TABLE Empresa (
    id int auto_increment,
    cnpj varchar(17) not null,
    data_atualizacao datetime not null,
    data_criacao datetime not null,
    razao_social varchar(100) not null,
    primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Funcionario (
    id int auto_increment,
    cpf varchar(14) not null,
    data_atualizacao datetime not null,
    data_criacao datetime not null,
    email varchar(100) not null,
    nome varchar(100) not null,
    perfil varchar(50) not null,
    qtd_horas_almoco double,
    qtd_horas_trabalho_dia double,
    senha varchar(255) not null,
    valor_hora decimal(10,2),
    empresa_id int not null,
    primary key(id),
    foreign key(empresa_id) references Empresa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Lancamento (
    id int auto_increment,
    data datetime not null,
    data_atualizacao datetime not null,
    data_criacao datetime not null,
    funcionario_id int not null,
    descricao varchar(255) not null,
    localizacao varchar(255) not null,
    tipo varchar(50) not null,
    primary key(id),
    foreign key(funcionario_id) references Funcionario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
