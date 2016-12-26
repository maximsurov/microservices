

Lets 
# Kubernetes + docker + Spring boot + Fabric8 maven plugin in a nutshell

## Overview

In this short article I am going to provide you with the information of how you can develop microservices components to run under Kubernetes + docker with the development perspectives. We are going to discuss of how kubernetes + docker + Spring boot + fabric8 maven plugin can work in conjunction with each other.

Microservices. How could we concisely illustrate architecture of microservices?  Let's refer to Roerich Banner Of Peace picture: https://en.wikipedia.org/wiki/Banner_of_Peace

![](docs/img/BannerOfPeace.png?raw=true "Roerich")

Here you can see the idea. The awesome tool to implement self-healing circle is Kubernetes. To conduct kubernetes kubectl command line tool is indented to be used. Plus there is fabric8 maven plugin to facilitate Java developers to work with kubernetes.

Kubernetes is awesome tool but there is a gap between skills that should be applied to run kubernetes cluster and general Java developer skills. From this point kubernetes could be treated ad a tool to be used by devops experts.

Hopefully, there are tools which greatly simplify deployment of kubernetes cluster under developers machines. One of those tools is minikube. This allows us to quickly run Kubernetes single node cluster.
Hopefully, by the end of this article reading you will manage to:

- run kubernetes cluster
- develop simple Spring boot microservices
- deploy microservices into kubernetes by using fabric8 maven plugin ot kubectl tool
- scale particular service


## Minikube

Prerequisite:
1.Windows machine with Oracle VM installed + maven
2.Refer to: https://github.com/kubernetes/minikube
3.As for me I downloaded binaries: kubectl.exe, minikube.exe
4.Added them to PATH
![](docs/img/path.png?raw=true "path")

![](docs/img/location.png?raw=true "location")

5. Create single node kubernetes cluster. You should execute:

     minikube start --host-only-cidr="192.168.99.1/24"

     (--host-only-cidr="192.168.99.1/24") this is for correct minikubeVM configuration: NAT (to provide IP address to be accessed from window machine) + Host-only adapter

     After this command has been executed minikubeVM ready to be used is being produced (it is running under CoreOS).  Virtual machine is created automatically.
     
 ![](docs/img/minikube.png?raw=true "minikube")




