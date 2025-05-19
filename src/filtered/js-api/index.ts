/**
 * Red Hat Trusted Profile Analyzer :: Exhort :: JavaScript API
 *
 * This is the main entry point for the Exhort API library.
 * It exports all the public APIs from both v3 and v4 models.
 */

// Re-export all models from v3
export * from './model/v3';

/**
 * Exhort API v4 Models
 *
 * This file exports all the models for the v4 version of the Exhort API.
 */

export * from './model/v4';

// Export version information
export const VERSION = '0.0.0'; // This will be replaced during build time 