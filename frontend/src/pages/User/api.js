import http from "@/lib/http";

export function loadUser(id) {
  return http.get(`/api/v1/users/${id}`);
}
