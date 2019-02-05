package org.tlh.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.exam.dto.ServiceInstanceDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/5
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("${scca.rest.context-path:}/service")
public class RegisterServiceController {

    @Autowired
    public DiscoveryClient discoveryClient;

    @GetMapping
    public List<ServiceInstanceDto> getInstances(@RequestParam("serviceId") String serviceId){
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
        if(instances!=null){
            List<ServiceInstanceDto> collect = instances.parallelStream().map(instance -> {
                ServiceInstanceDto serviceInstanceDto = new ServiceInstanceDto();
                serviceInstanceDto.setSecure(instance.isSecure());
                serviceInstanceDto.setHost(instance.getHost());
                serviceInstanceDto.setPort(instance.getPort());
                serviceInstanceDto.setServiceId(instance.getServiceId());
                return serviceInstanceDto;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

}
