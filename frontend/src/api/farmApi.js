import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/farms"
});

export const createFarmAccount = (payload) => api.post("", payload);
export const getFarmAccounts = () => api.get("");
export const addCropRecord = (accountId, payload) => api.post(`/${accountId}/records`, payload);
export const getCropRecords = (accountId) => api.get(`/${accountId}/records`);
export const getProfit = (accountId) => api.get(`/${accountId}/profit`);
