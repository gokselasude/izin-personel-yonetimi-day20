# --- Aşama 1: Angular uygulamasını derle ---
FROM node:20-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# --- Aşama 2: Derlenen dosyaları nginx ile servis et ---
FROM nginx:alpine

COPY --from=build /app/dist/izin-personel-yonetimi-frontend/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
