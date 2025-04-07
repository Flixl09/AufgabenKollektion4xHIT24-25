import { fileURLToPath, URL } from 'node:url';
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import vueDevTools from 'vite-plugin-vue-devtools';
import {VitePWA} from "vite-plugin-pwa";

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
    VitePWA({
      registerType: 'autoUpdate',
      manifest: {
        name: 'Flixls toller Webshop',
        short_name: 'Flixls',
        start_url: '/',
        display: 'standalone',
        theme_color: '#377bf5',
        background_color: '#000000',
        icons: [
          {
            src: 'src/assets/logo.png',
            sizes: '96x96',
            type: 'image/png',
          },
        ],
      },
      workbox: {
        mode: 'development',
        navigateFallback: 'index.html',
        runtimeCaching: [
          {
            urlPattern: /^https:\/\/flixl\.com\/.*$/,
            handler: 'NetworkFirst',
            options: {
              cacheName: 'flixl-cache',
            },
          },
        ],
      },
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  }
});
