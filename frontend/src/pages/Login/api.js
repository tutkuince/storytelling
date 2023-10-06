import http from "@/lib/http";

export const login = (credentials) => {
  return http.post("/api/v1/auth", credentials);
};
