import http from "k6/http";
import { check } from "k6";
import { sleep } from "k6";

export const options = {
  stages: [
    { duration: "10s", target: 8000 }, // 100 usuários em 10 segundos
  ],
};

export default function () {
  const url = "http://localhost:8080/proposal"; // Alterar para a URL do seu endpoint
  const payload = JSON.stringify({
    nome: "Teste",
    idade: 30,
    email: "teste@example.com",
  });

  const params = {
    headers: {
      "Content-Type": "application/json",
    },
  };

  const res = http.post(url, payload, params);

  // Verifica se o status é 201 (Created)
  check(res, {
    "status é 201": (r) => r.status === 201,
  });

  sleep(1); // Pause entre as requisições
}
