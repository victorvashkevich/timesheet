package vvv.timesheet.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vvv.timesheet.model.TimeSheet;
import vvv.timesheet.service.TimeSheetService;

@Component
public class TimeSheetConverter implements Converter<String, TimeSheet> {

    private TimeSheetService timeSheetService;

    @Autowired
    public void setTimeSheetService(TimeSheetService timeSheetService) {
        this.timeSheetService = timeSheetService;
    }

    @Override
    public TimeSheet convert(String id) {
        int intId = Integer.parseInt(id);
        if (id==null || intId==0) {
            return null;
        }
        return timeSheetService.getById(intId);
    }
}
