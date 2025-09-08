#!/bin/bash
echo Creando jar del Servidor Central...
cd ./ServidorCentral
cd ./TrabajoUY_Utilidades
cp source $HOME
cd ..
mvn install
echo Jar creado con exito

echo Creando war del Servidor Web...
cd ../ServidorWeb
mvn install
echo War creado con exito