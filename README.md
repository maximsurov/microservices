 
# Kubernetes + docker + Spring boot + Fabric8 maven plugin in a nutshell

## Overview

In this short article I am going to provide you with the information of how you can develop microservices components to run under Kubernetes + docker with the development perspectives. We are going to discuss of how kubernetes + docker + Spring boot + fabric8 maven plugin can work in conjunction with each other.

Microservices. How could we concisely illustrate architecture of microservices?  Let's refer to Roerich Banner Of Peace picture: https://en.wikipedia.org/wiki/Banner_of_Peace
![](docs/img/BannerOfPeace.png?raw=true "Roerich")
Here you can see the idea. The awesome tool to implement self-healing circle is Kubernetes. To conduct kubernetes kubectl command line tool is indented to be used. Plus there is fabric8 maven plugin to facilitate Java developers to work with kubernetes.

9Kubernetes is awesome tool but there is a gap between skills that should be applied to run kubernetes cluster and general Java developer skills. From this point kubernetes could be treated ad a tool to be used by devops experts.

Hopefully, there are tools which greatly simplify deployment of kubernetes cluster under developers machines. One of those tools is minikube. This allows us to quickly run Kubernetes single node cluster.
Hopefully, by the end of this article reading you will manage to:

- run kubernetes cluster
- develop simple Spring boot microservices
- deploy microservices into kubernetes by using fabric8 maven plugin ot kubectl tool- scale particular service


## Minikube

Prerequisite:

1. Windows machine with Oracle VM installed + maven

2. Refer to: https://github.com/kubernetes/minikube

3. As for me I downloaded binaries: kubectl.exe, minikube.exe

4. Added them to PATH

![](docs/img/path.png?raw=true "path")
![](docs/img/location.png?raw=true "location")

5. Create single node kubernetes cluster. You should execute:
     minikube start --host-only-cidr="192.168.99.1/24"
     (--host-only-cidr="192.168.99.1/24") this is for correct minikubeVM configuration: NAT (to provide IP address to be accessed from window machine) + Host-only adapter

     After this command has been executed minikubeVM ready to be used is being produced (it is running under CoreOS).  Virtual machine is created automatically.
      ![](docs/img/minikube.png?raw=true "minikube")


6. Execute minikube ip – to discover address of kubernetes machine (or you could login to virtual machine and execute: ifconfig)

  ![](docs/img/ip.png?raw=true "minikube ip")


7. Kubernetes + docker machine has been just created.

8. Some local settings to be applied:
Take config file from

 ![](docs/img/settings.png?raw=true "settings")
  And place it somewhere you feel it is convenient. (e.g. I placed it under root of D:/). We should use this file to execute kubectl commands.  

19.  E.g. refer to example that expands this artical:  https://github.com/maximsurov/microservices

10. Now we can build project and build docker image on host where kubernetes + docker is being run.
    At first you have to set:

    SET DOCKER_HOST=tcp://192.168.99.110:2376
    SET DOCKER_CERT_PATH=C:\Users\Maxim_Surov\.minikube\certs
    (this variable have to be set up only if kubernetes cluster is being accessed via SSL)

    You can discover those params by executing:

    ![](docs/img/environment.png?raw=true "environment") 
     
    Then build:

    mvn clean install fabric8:build

It should create executable Spring boot jar + create docker image on remote host.
If we execute: <<docker images>> on kubernetes + docker host we should see that surov/spring-boot-microservice image has been created.

11. Now it is time to deploy image into kubernetes. From windows host execute consequently 

 kubectl run spring-boot-microservice --image=surov/spring-boot-microservice --port=8080 --replicas=3 --kubeconfig="d:/config"
 
 kubectl expose replicationController spring-boot-microservice --type=NodePort --external-ip={ip of your minikube} --kubeconfig="d:/config"
 
 kubectl describe services spring-boot-microservice --kubeconfig="d:/config"
 
 kubectl get pods --kubeconfig="d:/config"

 alternative approach is just to use fabric8 maven plugin commands to accomplish it, e.g.:
 
 mvn clean install fabric8:build fabric8:run
 
 to scale:
 
 mvn fabric8:start -Dfabric8.replicas=3
 
 By default service that has one pod is created
 ![](docs/img/single.png?raw=true "single")
 
 if e.g. service has been scaled to 3 replicas the output is:
 ![](docs/img/whole.png?raw=true "scaled")
 
 We have just set up single node 3 replicas (SLA) kubernetes cluster. You can see that all replicas are run.

 To execute it externally we should use: http://192.168.99.101:32598/
 Or it could have been configured to use also 8080 port.
 
 ![](docs/img/replica1.png?raw=true "replica")
 ![](docs/img/replica2.png?raw=true "replica")
 
 As you can see different replicas play periodically.

Other useful commands:

 get pods --kubeconfig="d:/config"

 get nodes --kubeconfig="d:/config"

 get services --kubeconfig="d:/config"

 kubectl cluster-info --kubeconfig="d:/config" 
You can also leverage different capabilities of fabric8 maven plugin (https://maven.fabric8.io/)

 
## Summary
You have just managed to run single node kubernetes cluster. Plus, you can communicate with it to deploy, scale, etc services by using fabric8 maven plugin.
From docker perspectives there is counterpart of fabric8 maven plugin. It is called: com.spotify (docker-maven-plugin) though it is also powerfull it is indented to work only with docker, To work with kubernetes you should rely on fabric8 maven plugin.
Thank you for reading. I wanted to share this knowledge and approach. Since I spent a lot of time trying to set up kubernetes (with Ubuntu, etc). It seems it is the most comfortable solution (for general Java developer not for linux guy) just to try locally.
 
