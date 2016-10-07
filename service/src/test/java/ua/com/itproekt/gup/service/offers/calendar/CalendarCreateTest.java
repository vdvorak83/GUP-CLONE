package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.service.offers.CalendarStatusService;
import ua.com.itproekt.gup.service.offers.CalendarStatusServiceImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Конструктор
 * ===========
 * #1. Две стоимости (будни/выходные); Две даты (начальная/конечная):
 *     --------------------------------------------------------------
 *     - За дефолтный период (три полных месяца) устанавливается стоимость с учетом будней и выходных;
 *       -- дефолтный период (три полных месяца) определяется с учетом указанных дат (начальной/конечной)
 *       -- через конструктор с параметрами
 *     - За указанный период (начальной - конечной даты) устанавливается специальная стоимость;
 *       -- можно через дефолтный конструктор (без параметров)
 *
 * #2. Две стоимости (будни/выходные); Без даты (пустой массив):
 *     ---------------------------------------------------------
 *     - За дефолтный период (три полных месяца) устанавливается стоимость с учетом будней и выходных;
 *       -- дефолтный период (три полных месяца) определяется с учетом текущей даты
 *       -- через конструктор с параметрами
 *
 * #3. Одна стоимость (на каждый день); Две даты (начальная/конечная):
 *     ---------------------------------------------------------------
 *     - Только за указанный период (начальной - конечной даты) устанавливается специальная стоимость;
 *       -- Можно устанавливать специальную стоимость только на один день (начальная/конечная даты совпадают)
 *       -- можно через дефолтный конструктор (без параметров)
 *
 * #4. Одна стоимость (на каждый день); Без даты (пустой массив):
 *     ----------------------------------------------------------
 *     - За дефолтный период (три полных месяца) устанавливается специальная стоимость;
 *       -- дефолтный период (три полных месяца) определяется с учетом текущей даты
 *       -- можно через дефолтный конструктор (без параметров)
 *
 * Иннициализация
 * ==============
 * #1. Установка стоимости через конструктор с параметрами;
 *     -- (не является объязательным)
 * #2. Иннициализация периода-дней, через метод addPrices(), согласно ранее указанной стоимости;
 *     -- можно использовать с дефолтным конструктором (без параметров)
 */

public class CalendarCreateTest {

    private static final String formatter = "d.MM.yyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String PATH = "src/test/resources",
            RENTCALENDAR_FILE_NAME = "offerCalendar1.json",
            RENT_FILE_NAME = "offerRents.json"; //FIXME: file.properties

    private JsonObject jsonCalendars,jsonRents;
    private Gson gsonStatusCalendarDefault,gsonStatusCalendar1;
    private Map<String, Calendar> calendarPrices; //TODO: правилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, Rent> rents; //TODO: общая таблица в базе данных для аренды...
    private CalendarStatusService statusCalendar;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());
        gsonStatusCalendarDefault = new Gson();
        gsonStatusCalendar1 = new Gson();

        statusCalendar = new CalendarStatusServiceImpl(10000l,15000l); // Устанавливаем цену по умолчанию (на будни и выходные дни)
    }

    @After
    public void tearDown() {
        jsonCalendars = null;
        jsonRents = null;
        gsonStatusCalendarDefault = null;
        gsonStatusCalendar1 = null;
        calendarPrices = null;
        rents = null;
        statusCalendar = null;
    }

    /**
     * Устанавливаем цену на специальные дни (специальных дней может быть неогрниченно много в пределах выбранного периода..)
     * (запрос устанавливает: единую стоимость; стоимость на будни и выходные дни; стоимость на специальные дни;)
     */
    @Test
    public void testOwnerToStringPrices(){
        System.out.println("--------------------[ testOwnerToStringPrices ]");
//        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));
//        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar.addPrices(calendarPrices.get("scheme6").getPrice(), convertDate(calendarPrices.get("scheme6").getDays()));
//        statusCalendar.addPrices(calendarPrices.get("scheme7").getPrice(), convertDate(calendarPrices.get("scheme7").getDays()));

        System.out.println(statusCalendar);
//        System.err.println("Calendar-Status (year): " + gsonStatusCalendarDefault.toJson(statusCalendar)); // 'PriceScheme'
    }

    /**
     * Test(s) Scheme-Default
     * Java object to JSON, and assign to a String
     */
    @Test
    public void testOwnerToJSONPrices(){
        System.out.println("--------------------[ testOwnerToJSONPrices ]");
//        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
//        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));
        statusCalendar.addPrices(calendarPrices.get("scheme7").getPrice(), convertDate(calendarPrices.get("scheme7").getDays()));

//        System.err.println("Calendar-Status: " + gsonStatusCalendarDefault.toJson(statusCalendar)); // 'PriceScheme'
        System.err.println("Calendar-Status: " + statusCalendar.toJson()); // 'PriceScheme'
        System.err.println("Calendar-Price: " + gsonStatusCalendar1.toJson(calendarPrices)); // 'Calendars'
        System.out.println(statusCalendar);
    }

    /**
     * <DAY.MONTH.YEAR> >> (Long)
     * ("30.06.2014" >> 1472936400000)
     *
     * @param strDate
     * @return
     */
    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    /**
     * (Long) >> <DAY.MONTH.YEAR>
     * 1472936400000 >> "30.06.2014"
     *
     * @param lDate
     * @return
     */
    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    /**
     * @param[] strDate
     * @return[]
     */
    private Long[] convertDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = convertDate(strDate[date]);
        }
        return longDate;
    }

}
