import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields;

// prep stuff
def forYear = LocalDate.now().year
weekField = WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear();

// start with Monday of the first week of the year (sometimes the first day is in the last week of prev year)
def date = LocalDate.of(forYear, 1, 1).withDayOfMonth(1);
date = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
if (getWeekOfYear(date) > 1) date = date.plusWeeks(1)

// some output
def currentYear = date.year
println("Dumping all weeks for year $currentYear")

// loop until we have moved into the next year
while (date.year == currentYear) {
    def lastDayOfWeek = date.plusDays(6)
    println("KW ${getWeekOfYear(date)}: ${formatDate(date)} - ${formatDate(lastDayOfWeek, true)}")
    date = date.plusWeeks(1)
}


//
// ---- helper functions ---
//

def getWeekOfYear(LocalDate date) {
    return date.get(weekField)
}

def formatDate(LocalDate date, boolean includeYear = false) {
    if (includeYear) return date.format("dd MMM yyyy")
    else return date.format("dd MMM")
}