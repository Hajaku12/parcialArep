## Parcial AREP 2

# Avance hasta las 3:42

En este avance se termino la mayoria del codigo y se pudo conectar al servidor EC2 del Proxy, los otros dos servidores EC2. 
Se muestra por HTML el ingreso de los datos y se puede escoger entre Linear Search y Binary Search. Se uso solo un repositorio donde falta revisar la respuesta en JSON de la busqueda porque no la esta dando al presionarlo.

Para evidencia adjunto las imagenes:

- Imagen de los servidores EC2 
![img.png](images%2Fimg.png)
- Imagen de la aplicatción funcionando con el Servidor EC2:
![img_1.png](images%2Fimg_1.png)

    Url del servidor: http://ec2-98-83-233-221.compute-1.amazonaws.com:8080/
- Imagenes de los tres servidores funcionando, cada uno diferente

    - Servidor MathService1: ![img_2.png](images%2Fimg_2.png)
    - Servidor MathService2: ![img_3.png](images%2Fimg_3.png)
    - Servidor ProxyService: ![img_4.png](images%2Fimg_4.png)

# Avance después de las 3:42

#### Video del Funcionamiento
- Video de la aplicación funcionando: [video.mp4](images/video.mp4)

![videoFuncional](https://github.com/user-attachments/assets/be9bea01-a278-499b-ad84-144cbdd6bc1f)


### Instrucciones para ejecución

1. Clonar el repositorio de GitHub en cada una de las tres instancias de EC2 con el comando:
    ```sh
    git clone https://github.com/Hajaku12/parcialArep.git
    ```

2. Compilar el proyecto con el comando:
    ```sh
    mvn clean install
    ```

3. Elegir dos instancias de EC2 para que sean los servidores MathServices y ejecutar en cada una de estas máquinas el comando:
    ```sh
    mvn dependency:copy-dependencies
    java -cp "target/classes:target/dependency/*" org.example.Main
    ```
   Así deben lucir nuestras dos instancias:
   ![img_9.png](images%2Fimg_9.png)

4. Encender el servidor proxy con el comando:
    ```sh
    java -cp "target/classes:target/dependency/*" org.example.Main http://ec2-98-83-163-41.compute-1.amazonaws.com:8080 http://ec2-54-163-96-40.compute-1.amazonaws.com:8080
    ```
   
   Evidenciamos que el servidor está corriendo:
   ![img_8.png](images%2Fimg_8.png)

5. Probar los servicios:
  - Busqueda Linear con la Lista (10,20,13,40,60), Value = 13:
    ![img_6.png](images%2Fimg_6.png)
    
  - Busqueda Linear con la Lista (10,20,13,40,60), Value = 99:
    ![img_7.png](images%2Fimg_7.png)
  - Busqueda Binaria con la Lista (10,20,13,40,60), Value = 13:
    ![img_5.png](images%2Fimg_5.png)

