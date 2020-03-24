package vvv.timesheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import vvv.timesheet.converter.DepartmentConverter;
import vvv.timesheet.converter.EmployeeConverter;
import vvv.timesheet.converter.TimeSheetConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "vvv.timesheet")
@Import({AppSecurityConfig.class})
public class WebConfig implements WebMvcConfigurer {


    private EmployeeConverter employeeConverter;
    private DepartmentConverter departmentConverter;
    private TimeSheetConverter timeSheetConverter;

    @Autowired
    public void setEmployeeConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

    @Autowired
    public void setDepartmentConverter(DepartmentConverter departmentConverter) {
        this.departmentConverter = departmentConverter;
    }

    @Autowired
    public void setTimeSheetConverter(TimeSheetConverter timeSheetConverter) {
        this.timeSheetConverter = timeSheetConverter;
    }

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(employeeConverter);
        registry.addConverter(departmentConverter);
        registry.addConverter(timeSheetConverter);
    }
}
