# Listado de articulos 
- accediendo al endpoint /api/v1/articles tendremos el listado de articulos
```json
[
    {
        "productId": 1,
        "product": "Desmalezadora",
        "category": "Herramientas",
        "brand": "Makita",
        "price": "$9.600",
        "quantity": 0,
        "freeShipping": "SI",
        "prestige": "****"
    },
  {....}
]
```
- se podrá de igual manera poner filtros como query params
    - category= cadena de caracteres no case sensitive
    - brand= cadena de caracteres no case sensitive
    - freeShipping= palabra SI o NO, no case sensitive
    - order= numero de 0 a 3
    - prestige= cadena con al menos 1 y exclusivamente caracteres *
    - price= numero, se pueden incluir caracteres como $ . etc que no se tomaran en cuenta en la busqueda
    - product= cadena de caracteres no case sensitive
    
 # Procesamiento de compra
 - accediendo al endpoint /api/v1/purchase-request por POST podremos generar una solicitud de compra enviando un payload como el siguiente
 ```json
{
    "articles": [
        {
            "productId": 1,
            "name": "test",
            "brand": "tets",
            "quantity": 1
        }
    ]
}
```
- cada producto sera identificado por el productId en el sistema, si el stock disponible y los parametros son correctos se procesara la compra haciendo el descuento en memoria de los productos.