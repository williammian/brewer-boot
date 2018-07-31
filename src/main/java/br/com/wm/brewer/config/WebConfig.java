package br.com.wm.brewer.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.wm.brewer.thymeleaf.BrewerDialect;

@Configuration
@EnableSpringDataWebSupport
@EnableCaching
@EnableAsync
public class WebConfig extends WebMvcConfigurerAdapter {

//	private ApplicationContext applicationContext;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.applicationContext = applicationContext;
//	}
	
//	@Bean
//	public ViewResolver jasperReportsViewResolver(DataSource datasource) {
//		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//		resolver.setPrefix("classpath:/relatorios/");
//		resolver.setSuffix(".jasper");
//		resolver.setViewNames("relatorio_*");
//		resolver.setViewClass(JasperReportsMultiFormatView.class);
//		resolver.setJdbcDataSource(datasource);
//		resolver.setOrder(0);
//		return resolver;
//	}

//	@Bean
//	public ViewResolver viewResolver() {
//		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//		resolver.setTemplateEngine(templateEngine());
//		resolver.setContentType("text/html;charset=UTF-8"); //necessario
//		resolver.setCharacterEncoding("UTF-8");
//		resolver.setOrder(0);
//		return resolver;
//	}
//
//	@Bean
//	public TemplateEngine templateEngine() {
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setEnableSpringELCompiler(true);
//		engine.setTemplateResolver(templateResolver());
//		
//		engine.addDialect(new LayoutDialect());
//		engine.addDialect(new BrewerDialect());
//		engine.addDialect(new DataAttributeDialect());
//		engine.addDialect(new SpringSecurityDialect());
//		return engine;
//	}
//
//	private ITemplateResolver templateResolver() {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setApplicationContext(applicationContext);
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		resolver.setCharacterEncoding("UTF-8"); //necessario
//		resolver.setTemplateMode(TemplateMode.HTML);
//		return resolver;
//	}
	
//	@Bean
//	public SpringSecurityDialect securityDialect() {
//	    return new SpringSecurityDialect();
//	}
	
	@Bean
	public BrewerDialect brewerDialect() {
		return new BrewerDialect();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		registry.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
		
		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		dateTimeFormatter.registerFormatters(registry);
	}
	
//	@Bean
//	public FormattingConversionService mvcConversionService() {
//		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
//		conversionService.addConverter(new EstiloConverter());
//		conversionService.addConverter(new CidadeConverter());
//		conversionService.addConverter(new EstadoConverter());
//		conversionService.addConverter(new GrupoConverter());
//		
//		//NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
//		//conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
//		
//		BigDecimalFormatter bigDecimalFormatter = new BigDecimalFormatter("#,##0.00");
//		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
//		
//		//NumberStyleFormatter integerFormatter = new NumberStyleFormatter("#,##0");
//		//conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
//		
//		BigDecimalFormatter integerFormatter = new BigDecimalFormatter("#,##0");
//		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);
//		
//		// API de Datas do Java 8
//		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
//		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
//		dateTimeFormatter.registerFormatters(conversionService);
//		
//		return conversionService;
//	}
	
//	@Bean
//	public LocaleResolver localeResolver() { //fixa o locale
//		return new FixedLocaleResolver(new Locale("pt", "BR"));
//	}
	
//	@Bean
//	public CacheManager cacheManager() {
//		//return new ConcurrentMapCacheManager(); //implementação simples
//		
//		//Cache usando Guava
//		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
//				.maximumSize(3)
//				.expireAfterAccess(20, TimeUnit.SECONDS);
//		
//		GuavaCacheManager cacheManager = new GuavaCacheManager();
//		cacheManager.setCacheBuilder(cacheBuilder);
//		return cacheManager;
//	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:/messages");
		bundle.setDefaultEncoding("UTF-8"); // http://www.utf8-chartable.de/
		return bundle;
	}
	
//	@Bean
//	public DomainClassConverter<FormattingConversionService> domainClassConverter() {
//		return new DomainClassConverter<FormattingConversionService>(mvcConversionService());
//	}
	
	//Buscando mensagens de validação
	@Bean
	public LocalValidatorFactoryBean validator() {
	    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	    validatorFactoryBean.setValidationMessageSource(messageSource());
	    
	    return validatorFactoryBean;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}

}
