module haiti.database {
    exports dev.struchkov.haiti.database.repository.manager;
    exports dev.struchkov.haiti.database.repository;
    exports dev.struchkov.haiti.database.util;

    requires spring.data.jpa;
    requires haiti.context;
    requires org.slf4j;
    requires haiti.filter;
    requires spring.tx;
    requires spring.data.commons;
}