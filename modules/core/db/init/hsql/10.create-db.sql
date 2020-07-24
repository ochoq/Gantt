-- begin GANTT_SEGMENT2
create table GANTT_SEGMENT2 (
    ID varchar(36) not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    --
    START_ date,
    END_ date,
    COLOR varchar(255),
    TASK_ varchar(255),
    TASK_SPAN2_ID varchar(36),
    --
    primary key (ID)
)^
-- end GANTT_SEGMENT2
-- begin GANTT_TASK_SPAN2
create table GANTT_TASK_SPAN2 (
    ID varchar(36) not null,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    --
    CATEGORY varchar(255),
    --
    primary key (ID)
)^
-- end GANTT_TASK_SPAN2
