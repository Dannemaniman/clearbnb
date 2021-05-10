import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      // same port as Java Express
      '/rest': 'http://localhost:4000',
      '/api': 'http://localhost:4000'
    }
  }
})
