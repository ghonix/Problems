package scheduling

import java.util.PriorityQueue

// Definition for an Interval.
class Interval {
    var start: Int = 0
    var end: Int = 0

    constructor(_start: Int, _end: Int) {
        start = _start
        end = _end
    }
}

data class EmployeeInterval(val employeeIndex: Int, val intervalIndex: Int, val interval: Interval)

class EmployeeFreeTimes {

    fun employeeFreeTime(schedule: ArrayList<ArrayList<Interval>>): ArrayList<Interval> {
        val queue = PriorityQueue<EmployeeInterval> { a, b ->
            a.interval.start - b.interval.start
        }
        val result = ArrayList<Interval>()

        for (i in schedule.indices) {
            queue.add(EmployeeInterval(employeeIndex = i, intervalIndex = 0, interval = schedule[i][0]))
        }

        var prevEi = queue.peek()
        while (queue.isNotEmpty()) {
            var currentEi = queue.poll()

            if (prevEi.interval.end >= currentEi.interval.start) {
                prevEi.interval.end = Math.max(currentEi.interval.end, prevEi.interval.end)
            } else if (prevEi != currentEi) {
                result.add(Interval(prevEi.interval.end, currentEi.interval.start))
                prevEi = currentEi
            }

            if (hasNextInterval(currentEi, schedule)) {
                queue.add(
                    EmployeeInterval(
                        currentEi.employeeIndex,
                        currentEi.intervalIndex + 1,
                        schedule[currentEi.employeeIndex][currentEi.intervalIndex + 1]
                    )
                )
            }

        }

        return result
    }

    private fun hasNextInterval(e: EmployeeInterval, schedule: ArrayList<ArrayList<Interval>>): Boolean {
        return  e.intervalIndex + 1 < schedule[e.employeeIndex].size
    }
}