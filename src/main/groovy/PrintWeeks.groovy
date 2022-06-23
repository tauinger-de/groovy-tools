import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields;

def forYear = LocalDate.now().year
weekField = WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear();

def date = LocalDate.of(forYear, 1, 1).withDayOfMonth(1);
date = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
if (getWeekOfYear(date) > 1) date = date.plusWeeks(1)

def currentYear = date.year
println("Dumping all weeks for year $currentYear")

while (date.year == currentYear) {
    def lastDayOfWeek = date.plusDays(6)
    println("KW ${getWeekOfYear(date)}: ${formatDate(date)} - ${formatDate(lastDayOfWeek, true)}")
    date = date.plusWeeks(1)
}


def getWeekOfYear(LocalDate date) {
    return date.get(weekField)
}

def formatDate(LocalDate date, boolean includeYear=false) {
    if (includeYear) return date.format("dd MMM yyyy")
    else return date.format("dd MMM")
}