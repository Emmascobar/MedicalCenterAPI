package com.medicalcenterapi.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component
public class InterceptorClass implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // GENERATE-CODE -> IMPLEMENTS METHODS

    // Este metodo se ejecuta antes de que inicie el metodo del request (lo intercepta). Sí es true, continua al siguiente, el metodo POSTHANDLER.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Request: pedido de salida.
        // Response: respuesta de entrada.
        // Object handler: guarda toda la informacion del metodo que se esta ejecutando (tiene nombres, argumentos, anotaciones, todo);
        log.info("Registrando Metodo..." + handler.getClass().getName());
        long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);

        Random random = new Random();
        Integer demora = random.nextInt(500);
        Thread.sleep(demora); //Con esto simulamos un tiempo de demora, de carga.
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long tiempoFin = System.currentTimeMillis();
        long tiempoInicio = (long) request.getAttribute("tiempoInicio");
        long tiempoTranscurrido = tiempoFin - tiempoInicio;
        if(modelAndView != null){
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }
        log.info("Tiempo Transcurrido" + tiempoTranscurrido + " milisegundos.");
        log.info("Saliendo del Metodo..." + handler.getClass().getName());

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
