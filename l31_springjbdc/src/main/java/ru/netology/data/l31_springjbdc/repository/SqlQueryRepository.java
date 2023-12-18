package ru.netology.data.l31_springjbdc.repository;

public interface SqlQueryRepository {

    /**
     * Возвращает шаблон запроса из файла
     *
     * @param fileName Имя файла в classpath ресурсов
     * @return Шаблон sql запроса
     */
    String getSql(String fileName);
}
