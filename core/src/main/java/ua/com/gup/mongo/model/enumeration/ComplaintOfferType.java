package ua.com.gup.mongo.model.enumeration;

public enum ComplaintOfferType {

    TOPIC_MISMATCH("Неверная категория"),
    PROFANITY("Ненормативная лексика"),
    CONTENT_PROHIBITED("Запрещенный товар / услуга"),
    CONTENT_MISMATCH("Информация не соответствует заявленной"),
    IRRELEVANT("Объявление не актуально"),
    AGENCY("Агентство в рубрике от частных лиц"),
    FRAUD("Мошенник"),
    OTHER("Другое");

    private final String name;

    ComplaintOfferType(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }

}