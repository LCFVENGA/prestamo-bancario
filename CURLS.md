# Pruebas de la API

La API usa `http://localhost:8080` y el frontend usa `http://localhost:4200`.

## 1. Login de usuario

```powershell
$userLogin = curl.exe -s -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"user\",\"password\":\"user123\"}" | ConvertFrom-Json
$userToken = $userLogin.token
$userToken
```

## 2. Login de administrador

```powershell
$adminLogin = curl.exe -s -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"admin123\"}" | ConvertFrom-Json
$adminToken = $adminLogin.token
$adminToken
```

## 3. Crear solicitud como usuario autenticado

El backend ignora el `userId` recibido y usa el usuario del token. El valor `0` solo mantiene compatible el contrato del DTO.

```powershell
curl.exe -i -X POST http://localhost:8080/api/prestamos/prestamo `
  -H "Authorization: Bearer $userToken" `
  -H "Content-Type: application/json" `
  -d '{"userId":0,"amount":10000,"termMonths":12}'
```

## 4. Consultar mis préstamos

```powershell
curl.exe -i http://localhost:8080/api/prestamos/me `
  -H "Authorization: Bearer $userToken"
```

## 5. Listar préstamos como administrador

```powershell
curl.exe -i http://localhost:8080/api/prestamos `
  -H "Authorization: Bearer $adminToken"
```

## 6. Aprobar o rechazar

Reemplaza `1` por el ID real del préstamo.

```powershell
curl.exe -i -X PUT http://localhost:8080/api/prestamos/aprobar/1 `
  -H "Authorization: Bearer $adminToken"

curl.exe -i -X PUT http://localhost:8080/api/prestamos/rechazar/1 `
  -H "Authorization: Bearer $adminToken"
```

## 7. CRUD de usuarios como administrador

```powershell
curl.exe -i http://localhost:8080/api/usuarios `
  -H "Authorization: Bearer $adminToken"

curl.exe -i -X POST http://localhost:8080/api/usuarios `
  -H "Authorization: Bearer $adminToken" `
  -H "Content-Type: application/json" `
  -d '{"username":"cliente2","password":"cliente123","role":"USER"}'

curl.exe -i -X PUT http://localhost:8080/api/usuarios/3 `
  -H "Authorization: Bearer $adminToken" `
  -H "Content-Type: application/json" `
  -d '{"username":"cliente2-editado","password":"cliente123","role":"USER"}'

curl.exe -i -X DELETE http://localhost:8080/api/usuarios/3 `
  -H "Authorization: Bearer $adminToken"
```

## 8. Comprobación de autorización

Este comando debe responder `403 Forbidden` para un usuario normal.

```powershell
curl.exe -i -X PUT http://localhost:8080/api/prestamos/aprobar/1 `
  -H "Authorization: Bearer $userToken"
```
