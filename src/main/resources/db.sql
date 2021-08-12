create table ms_role
(
    id int auto_increment comment '主键id'
        primary key,
    role_name varchar(100) not null comment '角色名',
    auth_permissions varchar(255) default '["*"]' not null comment '权限列表',
    is_delete tinyint(1) default 0 not null comment '逻辑删除 0-否 1-已删除'
)
    comment '角色表';

create table ms_room
(
    id int auto_increment comment '自增id'
        primary key,
    room_name varchar(100) not null comment '房间名称',
    room_desc varchar(500) null comment '房间简介',
    room_number varchar(10) not null comment '房间号',
    room_password varchar(50) null comment '房间密码',
    manager_id int not null comment '房主用户id',
    is_delete tinyint(2) default 0 not null comment '逻辑删除 0-否 1-是',
    constraint ms_room_room_number_uindex
        unique (room_number)
)
    comment '房间表';

create table ms_user
(
    id int auto_increment comment '自增id',
    openid varchar(255) not null comment '用户openId',
    unionid varchar(255) not null comment '用户unionId',
    nick_name varchar(255) not null comment '用户昵称',
    gender tinyint(2) default 0 not null comment '性别 0-未知 1-男性 2-女性',
    avatar_url varchar(255) null comment '用户微信头像',
    phone_number varchar(11) null comment '用户手机号码',
    role_id int default 1 not null comment '角色id',
    status int default 0 not null comment '状态 0-正常 -1-禁用',
    is_delete tinyint(2) default 0 not null comment '逻辑删除 0-否 1-是',
    add_time datetime not null comment '创建时间',
    constraint ms_user_id_uindex
        unique (id)
)
    comment '用户表';

alter table ms_user
    add primary key (id);

