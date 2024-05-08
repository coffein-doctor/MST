# 빌드 환경
# Use an official Node runtime as a parent image
FROM node:20.11.0-alpine AS builder

# Set the working directory to /app
WORKDIR /

# Copy the package.json and package-lock.json files to the container
# package.json, package-lock.json을 워킹 디렉토리에 복사
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files to the container
# 현재 디렉토리의 모든 파일을 도커 컨테이너의 워킹 디렉토리에 복사
COPY . .

# Build the production version of the app
RUN npm run build

# Expose the port that the app will run on 3000
EXPOSE 3000

# npm start 스크립트 실행
CMD ["npm", "start"]